## 组合模式

- 什么是组合模式
- 生活中的组合模式
- 组合模式的实际运用
- 为什么使用js继承

[js继承文献](http://www.zcfy.cc/article/master-the-javascript-interview-what-s-the-difference-between-class-amp-prototypal-inheritance-2185.html)

官方：
```组合模式，将对象组合成树形结构以表示“部分-整体”的层次结构，组合模式使得用户对单个对象和组合对象的使用具有一致性。掌握组合模式的重点是要理解清楚 “部分/整体” 还有 ”单个对象“ 与 "组合对象" 的含义。```

- 好了，你可以忽略我上面说的废话，下面听我BB。


传说中的`23`中设计模式的命名者已经告诉大家这大概是一个什么样的套路，但我们以后要讨论的不仅仅要了解，还可以找机会使用。
### 生活中的组合模式
生活中还是有很多的组合模式的：`麦当劳套餐`，`饭店美团X人套餐`，`联通流量包`等等，他们把多个‘个体’组合成了一个‘整体’，这是生活中的例子，让我用最近的实际小项目来说话。
### 举个栗子：

最近在公司有一个小模块叫‘甘特图’，`github`基本都是这样的：
![](https://user-gold-cdn.xitu.io/2017/10/18/10da13822cd3bdd9f4211ea12e4527ef)


但是公司怎能要这种传统的东西呢，必须要创新！！！具体有啥呢，传统只有`一条线`，老板：给我加`两条`，一条`预期`，一条`实际`，要有`mileStone`（里程碑），加个`总进度`，加个....云云，信心满满的你是否被吓到了呢，我个人实现了一份，不过可能使用组合模式会更好，我们下面简单尝试一下使用`组合模式`吧 😆。

首先，我们确定我们做一个组件名字就叫`Gantt`（肯德基）吧，组件包括什么呢：分为这几个小组件（套餐）：`画图`套餐（包括：进度条，背景网格，内容补充），`格式化`套餐，加上一些简单的工具类:获得最长时间，日期格式化，只是给大家举个栗子。

做好之后大概这个样子 ：
![](https://user-gold-cdn.xitu.io/2017/10/18/71d4f2617c2815d6969d8d37733204bc)
[github开源地址](https://github.com/pkwenda/gantt.js)

这里做一下规范，下文中，我将把Gantt叫做（商店）小组件叫做（套餐），组件内原型方法叫做（成员）。

- 先造一个继承器轮子↓
 

```
 //建立一个属于我们自己的Jquery,里面只有inheritObject、inheritPrototype两个方法，
    (function () {
    var util = {
        inheritObject: function (o) {//对象继承封装
            var F = function () {
            };
            F.prototype = o;
            return new F();
        },
        inheritPrototype: function (subclass, supperclass) {//原型继承封装
            var obj = this.inheritObject(supperclass.prototype);
            obj.constructor = subclass;
            subclass.prototype = obj;
        }
    };
    window.$ = window.util = util;
})(window);//把闭包变量弄到全局

var Gantt = function (data) {
    this.ganttData = data;
    this.children = [];
    this.element = null;
}

Gantt.prototype = {
    init: function () {
        throw new Error('此方法必须子类重写')
    },
    build: function () {
        throw new Error('此方法必须子类重写')
    },

}

/**
 * 创建 Gantt外层容器
 * @param name
 * @param parent
 * @constructor
 */
var Container = function (name, parent) {
    Gantt.call(this);
    this.name = name;
    this.parent = parent;
    this.init();//构建子容器的基本点(id,dom,name)
}
$.inheritPrototype(Container, Gantt); //


Container.prototype = {
    /**
     *重写父类init
     */
    init: function () {
        this.element = document.createElement('div');//创建一个div元素
        this.element.name = this.name;
        this.element.id = 'ganttView';
        this.parent.append(this.element);
    },
    /**
     *重写父类build
     */
    build: function (child, text) {
        child.append(document.createTextNode(text)); //添加测试描述
        this.children.push(child);
        this.element.appendChild(child);
        return this;
    },
    getElement: function () {
        return this.element;
    },
    draw: function () {
        this.parent.appendChild(this.element)
    }
}
//调用方法
var ganttView = new Container('GanttView', document.body);
ganttView.build(document.createElement("div"), '左侧详情项目1')
    .build(document.createElement("div"), '左侧详情项目2').build(document.createElement("div"), '右侧画图')
      
   
    ```
    
  
节约时间css就不写了,
```
    float:left;
    height,width,
    color,backbround,
    text:center.....脑补中...
    ```
那么我们注意,`Container`是个次级容器,是根据一个`parent`也就是最原始的'body'元素逐渐逐渐向里面画的,里面还有更加复杂的内容(计算最大时间范围，画背景，添加日历等等)
    
    
下面还应该有`item`里面增加`描述`，`详情`，`画图`等
    当然描述，详情，画图都只是‘套餐’，还是需要最底层的‘成员’来做地基的，成员是最基层的，他不再拥有子类，但是他们继承了父类。
    
那么我们整体修改一下代码：
```
  var Gantt = function (data) {
        this.ganttData = data;
        this.children = [];
        this.element = null;
    }

    Gantt.prototype = {
        init: function () {
            throw new Error('此方法必须子类重写')
        },
        build: function () {
            throw new Error('此方法必须子类重写')
        },

    }

    /**
     * 创建 Gantt外层容器
     * @param name
     * @param parent
     * @constructor
     */
    var Container = function (name, parent) {
        Gantt.call(this);
        this.name = name;
        this.parent = parent;
        this.init();//构建子容器的基本点(id,dom,name)
    }
    $.inheritPrototype(Container, Gantt); //


    Container.prototype = {
        /**
         *重写父类init
         */
        init: function () {
            this.element = document.createElement('div');//创建一个div元素
            this.element.id = 'ganttView';
            this.element.textContent= this.name;
            this.parent.append(this.element);
        },
        /**
         *重写父类build
         */
        build: function (child) {
//            child.append(document.createTextNode(text)); //添加测试描述
            this.children.push(child);
            this.element.appendChild(child.element);
            return this;
        },
        getElement: function () {
            return this.element;
        },
        draw: function () {
            this.parent.appendChild(this.element)
        }
    }

    /**
     * 创建 Gantt基础成员
     * @param name
     * @param parent
     * @constructor
     */
    var Item = function (name, parent) {
        Gantt.call(this);
        this.name = name;
        this.parent = parent;
        this.init();//构建子容器的基本点(id,dom,name)
    }
    $.inheritPrototype(Item, Container); //

    Item.prototype = {
        /**
         *重写父类init
         */
        init: function () {
            this.element = document.createElement('div');//创建一个div元素
            this.element.id = 'ganttItem';
            this.element.textContent = this.name;
//            this.parent.append(this.element);
//            return this.element;
        },
        /**
         *重写父类build
         */
        build: function (text) {
            //可以再画进度条,以及为进度条绑定点击事件
        },
        getElement: function () {
            return this.element;
        },
        draw: function () {
            this.parent.appendChild(this.element)
        }
    }

    //调用方法
    var container = new Container('GanttView', document.body);
        container.build(new Item('左侧详情项目1'))
                .build(new Item('左侧详情项目2'))
                
                ```
 
 ![](https://user-gold-cdn.xitu.io/2017/10/18/997cfd2b701a539045c4dad52aa460f3)
    
- 为什么要使用`继承`，贼麻烦.我直接`new`不行么？

 “行，但是记住一点，我们使用了组合模式就要保障接口的统一，这也是[面向对象编程](https://juejin.im/post/58ff6374570c350058f489b5)的思想，类似`java`的`interface`接口，这样我们处理回调、异常更加方便，简化了复杂的整体，又通过子类丰富了整体。“
    
我原来写的甘特图，是一次性生成的，如果产品提出，若使用`长轮循`监听到服务器，如果服务器增加了一个任务，以动画的形式动态添加一条进度条，这对于我以前的组件来说改动比较大，但是这个就很简单了，可能我们加一个add原型方法继承就好了。
    
- 那么组合模式有啥好处？
    “使得项目更加模块化，一个组件可以无限向下分成各个套餐，直到到达最底层的成员，以原子粒度书写代码，对于代码维护十分有利。”
    
    
开源的[Gantt插件](https://github.com/pkwenda/gantt.js)是基于`Jquery`的扩展插件，没有用到svg等笨重的组件，只依赖jquery，只有14k，还有一些亮点没有提交，当时写的比较匆忙，现在最近想想也许当时考虑用组合模式更加适合于以后需求的变更。极大节约开发时间。
    
### 最后希望大家能写出更加风骚的代码
 

