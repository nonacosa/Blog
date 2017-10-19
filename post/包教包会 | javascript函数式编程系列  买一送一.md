
`买一送一`：本系列可以让你掌握`函数式编程`，并且附赠 `underscore` 技能

---
### 日常BB
面对日新月异的`编程语言`都展开了函数式编程大战，灵活古老的`javascript`怎么不参战？`javascript`可是天然支持函数基础的元老人物。想要成为一名高逼格的程序员不管你是前端，还是后台，亦或是全栈，不管你开发`web`或`hybrid`，怎么能不掌握呢？笔者主要是`java`从业者，面向对象思想根深蒂固，让我们以`javascript`为基石，破而后立，重新学习`javascript`，进行函数编程，感受非同一般的编程乐趣。

---

要说`javascript`的`函数编程` `Jquery`可以少，但是`underscore`是必不可少的，不管你是否掌握`underscore`，后续系列文章可能会大量使用此库。

### 不得不说的第一课

- `call()`和`apply()`
 

掌握`call()`和`apply()`不是学好函数编程的关键，而是基石，先简单讲下：
其实`call` 和 `apply`的存在目的只有一个：改变函数整体内部`this`的指向，[this](https://segmentfault.com/a/1190000010432941) 这里就不老生常谈了，完全浪费大家时间，听说不举例子都是耍流氓，我举还不行嘛...


```
/**
 * Created by Venda-GM on 2017/10/18.
 */

function Peaple() {}
Peaple.prototype = {
    name:'小明',
    say:function(){
        alert(this.name);
    }
}

var Peaple1 = {
    name:'小强'
}

var peaple = new Peaple();
peaple.say.call(Peaple1); //小强
peaple.say.apply(Peaple1);//小强   
//******************知识点0.0***************
Fn1并没有say这个方法,但是fn原型有,那么fn.call...
```
可以看出来：
其中的`this`被指向了，`name`并不是原来`Peaple`中的`小明`了
- 结论：

`不管是call 还是 apply 都改变了函数的 this 对象`

那两个函数总有差距，具体差距呢`↓`
--`接受参数不一样`--
```
call()方法中的[其余的]参数必须直接传给函数

apply()接收两个参数：一个参数是运行时的作用域,
另一个是参数数组、或arguments等
```
- `arguments`是什么？

和`call`，appply一样，都是每个`function`内置的方法，`arguments`是属性，可以获取到传递到这个方法的全部变量。`一般在库中极为常见`问底注解`①_.toArray`就用了。

- 了解了之后我们组合起来做一个例子：

我们制造一个`函数`：它 接受一个函数，返回一个函数，并用`apply`执行返回来的函数。

```
        function splat(fun){ 
            return function(array){
                return fun.apply(console,array);
            }
        }
        var addArray = splat(function(x,y){
            this.log(x,y)
            return x+y;
        })
        addArray([1,2]); //3
        
        ```
        
先自己想1分钟，然后我来解析一下发生了啥？

![](https://user-gold-cdn.xitu.io/2017/10/19/3147f656dc04af503316778871056470)

我们调用`addArray`的时候`addArray`调用了`splat()`函数并向他传递了一个`函数`（我们简称`解决方案`吧），而他也没干啥好事，最终`splat()`返回的函数说：`“我也解决不了，你的方案不错，就用你的做吧。”` 说完大笔一挥, `fun.apply() `[同意！] 并且把你提交的`[1,2]`，按照你的解决方案执行了后还给你。

- 并且发现

最终`addArray`内部的`this`对象由`window`转变为了`console`。有人问这有个吊用？下面举例

```
        function splat(fun){ 
            return function(array){
                var math_π =[1,4,1,5,9,2,6,5];
                return fun.apply(math_π,array);
            }
        }
        var addArray = splat(function(x,y){
            this.push(x+y)
            console.log(this); //[1,4,1,5,9,2,6,5,3]
            return x+y;
        })
        addArray([1,2]); //3
        
        ```
        
我们有一个私有属性`math_π`，并不想设为全局，并且在执行`addArray`的匿名方法是还想让他对`math_π`搞事情，那么我们可以吧指针通过`apply`指向它，处理一些事情

我以前讲的`面向对象编程`说过，私有的面向对象处理方法是，制造原型链设置`get/set`方法，在`new`一个对象，`get`到即可，面向对象是容易理解，但是是不是有点向`java`一样繁琐了呢。

--- 
刚才例子讲了啥？健忘症又犯了！
讲了我们实现了一个函数它接受了一个函数，并且返回了一个函数，返回的函数执行了接受的函数，并且改变了作用域。

我们可以做一个相反的，若有这样一个需求：

我有个方法只接受数组，但是现在有个不可抗力让我只能传字符串，传多少个我也不知道，我该怎么办？

```
//原始方法
var F = function(array){
            return array.join(' ')
        }
```
想传的数据
```
1,2,3,4,5,7,7,zzz,www,ddd
```
我们首先想到`arguments`，那怎么原封不动F实现需求呢？用`call`！完全吻合

```
//做一个转换器
var ParamsConvertor  =  function(fun) {
    return function(){ //返回一个匿名函数
        fun.call(this,_.toArray(arguments));①
    }
}
```

我们调用下：

```
ParamsConvertor(F)(1,2,3,4,5,7,7,'zzz','www','ddd');
```
打印结果：
```
1 2 3 4 5 7 7 zzz www ddd
```
完美！

这还只是摸到了函数式的一些边缘就已经很兴奋了，正式开始进行编程会怎么样呢？

今天太晚了就先写到这里


ps：①`_.toArray`是`underscore`的一个函数，

```
toArray_.toArray(list) 
把list(任何可以迭代的对象)转换成一个数组，在转换 arguments 对象时非常有用。

(function(){ return _.toArray(arguments).slice(1); })(1, 2, 3, 4);
=> [2, 3, 4]

```
我们看到其实他也是运用了`arguments`对象。

-- -

资料：[underscore中文文档](http://www.bootcss.com/p/underscore/)
与`underscore`可以配合使用的还有[lodash中文文档](http://lodashjs.com/docs/)
目前先掌握`underscore`即可。

[文中代码 github 博客地址](https://github.com/pkwenda/blog/tree/master/code)

工作电脑没带，还没整理，近期将会将以前的，近期的，各个网站的博客整理一下到我的这个`git`库中，并写一个列表，感兴趣可以点下`star`，点`star`不迷路

---
接下来会正式踏足`函数式编程`，准备好了么，另外`设计模式`也会尽力持续更新，本来打算一个系列一个系列更新，但是根本按耐不住想写其他的，其实我最近更想写的是java，还想用`Electron`封装一个`elasticsearch`客户端、继续维护`爬虫框架`、想做的事情很多，慢慢来吧。

![](https://dn-mhke0kuv.qbox.me/c24affb7d0e592b4c4d0?imageView2/0/w/1280/h/960/ignore-error/1)

