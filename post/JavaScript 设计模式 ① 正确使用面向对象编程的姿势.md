
`javascript`是一门弱语言，他有着分同一般的灵活性使它迅速的成为几乎人人必会的一门语言，but，你们使用的姿势真的正确吗？

在以前的开发过程当中，老板：给我加个`验证用户邮箱`、`验证用户短信...`功能！
```
function checkMessage(){...}
function checkEmail(){...}
function ... //茫茫多的函数
```
这样写好了之后 `function` 是全局的变量，那么是全局的就难免会对项目javascript环境造成污染，可能会对其他同事造成影响，我们首先要考虑会不会影响别人，如果别人重名对你的`checkMessage`覆盖，那么这样的BUG是很难发现的。为了不造成太多的`全局污染`，我们可以这样写：
```
var checkObject = {
    checkMessage:function(){},
    checkEmail:function(){},
    ...
}
```
首先说明，并不是这样写就不会造成污染了，`checkObject`依旧是全局变量，那么好处在哪里呢？想一下如果Jquery的`$`被覆盖了，那么我们页面的
```
$.each | $.extends | $(#id) | $... 
```
都失效了，那么我们很容易定位问题: `Jquery`出问题了！`checkObject`也是一样,当checkObject出问题了，我们很容易定位错误。

调用：`checkObject.checkMessage()` 即可

那么问题又来了：如果有同事用我的checkObject搞一些事情呢？它可以直接用我的方法么？当然可以，问题是，你买了一本书你愿意别人乱写乱画么？我们可以改造一下：

```
var checkObject = function(){
    return {
        checkMessage:function(){},
        checkEmail:function(){},
        ...
    }
}
```
我们把方法通过`function`的调用返回，这样别人可以这样用 ： 

```
var check = checkObject();
check.checkEmail();
```

我们可以再完善一下，把`checkObject`看成java的类，`checkMessage`,`checkEmail`看成java的`public` 公有方法,既然看成类了，我们可以把`checkObject`写成大写`CheckObject`

```
var CheckObject = function(){
    this.checkMessage = function(){},
    this.checkEmail = function(){},
    ...
}
```
别人调用:

```
var check = new CheckObject(); //既然是一个类了，就要 new 来实例化了
check.checkEmail();
```
每一次通过`new`创建新对象的时候，新创建的对象都会对类`this`上的属性进行复制，你定义了两个那么就复制两次，那么再更多呢？是不是感觉有些奢侈呢，考虑我们可以运用javascript的原型`prototype`来创建它：
```
 var CheckObject = function(){
   
 }
 CheckObject.prototype.checkMessage = function(){},
 CheckObject.prototype.checkEmail = function(){},
 ...
```
你嫌麻烦？
```
 var CheckObject = function(){
   
 }
 CheckObject.prototype{
 checkMessage : function(){},
 checkEmail : function(){},
 ...
 }
 
```
这样我们的方法都复制到`CheckObject`的原型连上去了，创建出来的对象都是通过`prototype`依次寻找，都绑定在`CheckObject`的原型上`__proto__`
![](https://dn-mhke0kuv.qbox.me/3adc2b183e7a22203dc2)
随便看一下`Jquery`的原型链，是不是很熟悉的方法呢？

同志们是否好奇`Jquery`的方法是如何链式调用的呢？很简单，我来模拟一下
```
 var CheckObject = function(){
   
 }
 CheckObject.prototype = {
 checkMessage : function(){ ... return this },
 checkEmail : function(){ ... return this },
 ...
 
 var check = new CheckObject();
 check.checkMessage().checkEmail();
```
就这么简单，我们只需要将`this`指代的当前对象全部返回即可。

咳咳，言归正传，回到面向对象编程的课题上

![](https://dn-mhke0kuv.qbox.me/41d8887dd914abb2f0b7)

java中有 `private`声明的私有变量、 有通过`public`的`getter` `setter`方法进行通信，有`static`修饰的`静态变量`，`静态方法`，`有构造器`，那么javascript可以使用这样的设计模式么？可以，跟我往下看:

我们去商店买烟
```
var Smoke = function(id,name){
    //私有属性
    var num = 0;
    
    //对象的公有属性  （需要new）
    this.id = id;

    //私有方法
    function checkID(){ return true};

    //公有 setter getter 构造函数
    this.setName = function(name){
        this.name = name;
    }
    this.getName = function(){
        return this.name;
    }
    //对象的公有属性  （需要new）
    this.information = function(){
        //只有在Smoke内部才能调用checkID() 
        if(checkID()) return this.name+'香烟'+'订单号 :'+this.id

    } 
}
Smoke.prototype = {
    money:'10元',  //公有属性（不需要new） 直接Smoke.money[想没想到Array的length?]
    other:function(){}
}

var smoke = new Smoke(994857,'煊赫门');
smoke.information(); //"undefined香烟订单号 :994857" ps:因为我们没对Smoke的name属性赋值
smoke.setName('煊赫门'); //我们赋值
smoke.information(); //"煊赫门香烟订单号 :994857"
smoke.num; //undefined ps:很明显他是私有属性
smoke.checkID();//error is not function ps:很明显私有方法


```
如果我们没有`new`
```
var smoke = Smoke(994857,'煊赫门');
smoke.information(); //Uncaught TypeError: Cannot read property 'information' of undefined
```
纳尼报错了？
```
smoke.money; //undefind 
smoke //undefind 
```
(好像明白了什么......) 别急，让我们看下window
```
window.information(); // "undefined香烟订单号 :994857"
```
恍然大悟，因为new是可以对当前对象（Smoke)的`this`不停地赋值【上面讲过】，而上面的没有`new`相当于全局执行了`Smoke()` 所以是他的`this` 指向到 `window`去了！
怎么避免这种无操作呢？我们在Smoke内部进行类型检查：
```
var Smoke = function(id,name){
    var num = 0;
    function checkID(){ return true};
    //判断this在执行过程中是不是属于Smoke，如果是说明是new过的  0.0
    if(this instanceof Smoke){ 
        this.id = id;
        this.setName = function(name){
            this.name = name;
        }
        this.getName = function(){
            return this.name;
        }
        //对象的公有属性  （需要new）
        this.information = function(){
            //只有在Smoke内部才能调用checkID() 
            if(checkID()) return this.name+'香烟'+'订单号 :'+this.id
    
        }
    }else{
        return new Smoke(id,name);//内部重新new一个  0.0
    }
}
```
看完了么，感觉爽不，是不是感觉就是在写java呢，其实javascript就是灵活在这里，这只是javascript一种常用的面向对象设计模式，后面我会将更多的设计模式，这些设计模式都是经过前人无数心血总结出来给我们的，我们为什么不用呢？

感谢`[他居然爱吃虫]`同学对本文的校对

如果您觉得还算不错可以关注我持续看我的文章，大概方向：`前后端语言设计模式`、`如何设计好一款框架`、`源码导读`、`技术实践`。

- 青年才俊可以入群交流:`147255248`

 







