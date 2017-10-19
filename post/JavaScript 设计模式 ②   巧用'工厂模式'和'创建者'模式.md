我为什么把他们两个放在一起讲？我觉得这两个设计模式有相似之处，有时候会一个设计模式不能满足你的需求而采用另一种设计模式。基于这点考虑，而且为了大家更好地理解，我放到了一起，加深大家的印象，活学活用。
```
[这里我为了能更好的体现下设计模式与JS本体语言的结合，我用了一点继承关系.
有的同学都不知道JS能继承，就算大家不懂继承也希望大家能看下去，弄懂它！]
```
## 本文扩展
[掘金有个文章继承还是蛮透彻的](https://zhuanlan.zhihu.com/p/24964910?refer=muyichuanqi) 
[JS原型链与继承别再被问倒了](https://juejin.im/entry/58f94fcf1b69e600588227c0) 

 ## 工厂模式
- 创建对象跟对不同需求进行不同的实例化

在我们Team协作开发过程当中，不同于我们写个人项目，对全局变量的限制很大，我们要尽量少的使用全局变量，对于一类对象在不同需求上的不同使用，甚至将一些有些类似的方法抽象化，可以用工厂模式来负责创建这些对象，调用者可以使用一部分资源也可以在基础上私人订制一套资源。

就拿昨天入群的小伙伴举个栗子：他设计一个网页播放器有四个按钮：

![](https://dn-mhke0kuv.qbox.me/07760c52cadbc8d27ace)

我们不讨论他的实现方式，我们按照工厂模式来简单创建一个吧！

```
function wangyiMusicAction(action){
    var o = new Object;
    o.vender = '网易云音乐';
    o.playingMusic = 'see you again'
    switch (action){
        case 'last':
            o.information = {currentMusic:'Ich will',status:'200|404',message:'上一曲'}
            break
        case 'next':
            o.information = {currentMusic:'一人我编程累',status:'200|404',message:'下一曲'}
            break
        case 'play':
            o.information = {currentMusic:'see you again',status:'200|500',message:'播放'}
            break
        case 'mute':
            o.information = {currentMusic:'see you again',status:'200|500',message:'静音'}
            break
    }
    return o;

}
var music = wangyiMusicAction('next')
console.log('音乐提供商 : '+music.vender);
console.log('正在播放 : '+music.playingMusic);
console.log('执行动作 : ' +music.information.message);
console.log('接口状态 : ' +music.information.status);
console.log('执行动作后歌曲 : ' +music.information.currentMusic);

----------执行结果--------

音乐提供商 : 网易云音乐
正在播放 : see you again
执行动作 : 下一曲
接口状态 : 200|404
执行动作后歌曲 : 一人我编程累
```
这其实使我们经常使用的，不过这是面向过程的，不太符合我们的设计模式。我们用上篇学到的模式：`对象`


我们可以修改一下：

```
var WangyiMusicAction = function(action){

    this.vender = '网易云音乐';
    this.playingMusic = 'see you again'

}

WangyiMusicAction.prototype = {
    last : function() {
            this.information = {currentMusic:'Ich will',status:'200|404',message:'上一曲'}
        },

    next : function() {
            this.information = {currentMusic:'一人我编程累',status:'200|404',message:'下一曲'}
        },

    play : function() {
            this.information = {currentMusic:'see you again',status:'200|500',message:'播放'}
        },

    mute : function() {
            this.information = {currentMusic:'see you again',status:'200|500',message:'静音'}
        }

}

var music = new WangyiMusicAction()
music.next(); //执行下一曲动作
console.log('音乐提供商 : '+music.vender);
console.log('正在播放 : '+music.playingMusic);
console.log('执行动作 : ' +music.information.message);
console.log('接口状态 : ' +music.information.status);
console.log('执行动作后歌曲 : ' +music.information.currentMusic);
音乐提供商 : 网易云音乐
正在播放 : see you again
执行动作 : 下一曲
接口状态 : 200|400

----------执行结果--------

执行动作后歌曲 : 一人我编程累

```
这样就算是`面向对象`的了，虽然达到目的，但是上面所说的，但是这算是`Music`的网易云音乐实现版本、总不能再来一个`QQMusic`、`XiaMiMusic`吧？我们建立一个`Factory`工厂来管理所有的音乐：

```

var WangyiMusicAction = function(action){

    this.vender = '网易云音乐';
    this.playingMusic = 'see you again'

}

//为网易音乐提供共有方法
WangyiMusicAction.prototype = {
    last : function() {
        this.information = {currentMusic:'Ich will',status:'200|404',message:'上一曲'}
    },

    next : function() {
        this.information = {currentMusic:'一人我编程累',status:'200|404',message:'下一曲'}
    },

    play : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'播放'}
    },

    mute : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'静音'}
    }

}


var QQMusicAction = function(action){

    this.vender = 'QQ音乐';
    this.playingMusic = '其实我不low'

}

//为QQ音乐提供共有方法
QQMusicAction.prototype = {
    last : function() {
        this.information = {currentMusic:'Ich will',status:'200|404',message:'上一曲'}
    },

    next : function() {
        this.information = {currentMusic:'网易才low',status:'200|404',message:'下一曲'}
    },

    play : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'播放'}
    },

    mute : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'静音'}
    }

}
//音乐工厂
var MusicFactory = function(type){
    switch (type){
        case 'qq':
            return new QQMusicAction();

        case 'wangyi':
            return new WangyiMusicAction()
    }
}


var music = new MusicFactory('qq')
music.next(); //执行下一曲动作
console.log('音乐提供商 : '+music.vender);
console.log('正在播放 : '+music.playingMusic);
console.log('执行动作 : ' +music.information.message);
console.log('接口状态 : ' +music.information.status);
console.log('执行动作后歌曲 : ' +music.information.currentMusic);

----------执行结果--------

音乐提供商 : QQ音乐
正在播放 : 其实我不low
执行动作 : 下一曲
接口状态 : 200|404
执行动作后歌曲 : 网易才low

```
这样调用者需要用音乐接口，只需要记住`MusicFactory`就可以了，`MusicFactory`就像一个大工厂，对于`music`可以返回他要的一切。

好，我们回过头来看一下：

`第一种方法`:是创建一个新的对象 o 对他来增强 属性 的功能来实现的.
`第二种方法`:是实例化对象来创建的。
`第二种方法`:如果他们继承同一个父类 `BaseMusic` 那么他们父类的原型方法是可以和它们公用的! 
`第一种方法`:我们内部 new 了一个新的个体，就不能与父类共用了.

具体哪种还是看你需求的，不过我更倾向第二种，因为他扩展性高，需求多的时候我们甚至可以将通用的抽离出来放到父类BaseMusic中。

在下面的继承中我运用了类式继承
```
ps:(大家可以看看构造函数继承和组合继承链接在最下面）
```

```

//基类（父类）music方法
var BaseMusic = function(){
    this.playingMusic = 'see you again'
}
//实现通用方法
BaseMusic.prototype = {
    last : function() {
        this.information = {status:'200|404',message:'上一曲'}
    },

    next : function() {
        this.information = {currentMusic:'一人我编程累',status:'200|404',message:'下一曲'}
    },

    play : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'播放'}
    },

    mute : function() {
        this.information = {currentMusic:'see you again',status:'200|500',message:'静音'}
    }
}
//网易云的不同于父类的构造方法
var WangyiMusicAction = function(action){

    this.vender = '网易云音乐';

}
//这里通过prototype实现类继承
WangyiMusicAction.prototype = new BaseMusic();//这些动作我都放在基类了，达到代码复用的目的
 
//QQ
var QQMusicAction = function(action){

    this.vender = 'QQ音乐';
    this.playingMusic = '其实我不low'

}
QQMusicAction.prototype = new BaseMusic() //这些动作我都放在基类了，达到代码复用的目的

//音乐工厂
var MusicFactory = function(type){
    switch (type){
        case 'qq':
            return new QQMusicAction();

        case 'wangyi':
            return new WangyiMusicAction()
    }
}


var music = new MusicFactory('wangyi')
music.next(); //执行下一曲动作
console.log('音乐提供商 : '+music.vender);
console.log('正在播放 : '+music.playingMusic);
console.log('执行动作 : ' +music.information.message);
console.log('接口状态 : ' +music.information.status);
console.log('执行动作后歌曲 : ' +music.information.currentMusic);

----------执行结果--------

音乐提供商 : 网易云音乐
正在播放 : see you again
执行动作 : 下一曲
接口状态 : 200|404
执行动作后歌曲 : 一人我编程累
```

这样看起来是不是更好、更简洁呢？

## 创建者模式

- 工厂模式职责：我不管你想干啥，我只返回给你一个你想要的对象
- 创建者模式职责：主要针对复杂业务的`解耦`，算是工厂的一种拆解、拼接。我可以将你的需求分解多个对象创建，更关心的是创建对象的过程。


不复杂不能突显出他的魅力,举个稍微复杂栗子：

我们公司是卖车的，用户下单要买车，这个车呢：
```
品牌：迈巴赫、林肯、宾利、特斯拉[如果不选品牌，默认特斯拉] 
颜色：赤橙黄绿青蓝紫...[如果不选颜色，默认黄色]
动力：燃油、电力、混合动力[如果不选动力，默认电力]
购买人的一些备注信息[购买人可能会修改备注需要提供方法]
针对购买人选择的车型返回对车型的简单描述[描述可以修改]

```
最终根据用户选择来生成一个订单：
想下这用工厂模式是不是要写很多的if else来返回这么一个Car的对象呢？

我们先将  `车`  `购买人的动作`  `反馈 ` 分解为三个对象再在最后进行拼接 ：

```
//创建一个汽车
var Car  = function(param){
    this.color = param && param.color || 'yellow';
    this.brand = param && param.brand || 'Tesla';
    this.power = param && param.power || 'electric';
}
//提供原型方法
Car.prototype = {
    getColor : function () {
        return this.color;
    },
    getBrand : function () {
        return this.brand;
    },
    getPower : function () {
        return this.power;
    }
}

//创建一个反馈
var FeedBack = function(brand){
    var that = this;
    (function(brand,that){
        switch (brand){
            case 'Tesla':
                // that.brand = brand;
                that.information = '特斯拉是好车'
                break
            case 'Rolls' :
                that.information = '劳斯来时是好车'
        }
    })(brand,that)
}

FeedBack.prototype.changeBrand = function (information) {
    this.information = information;
}


//创建一个顾客
var Client = function(name,message){
    this.name = name;
    this.message = message || '无留言';
}
//顾客修改备注
Client.prototype.changeMessage = function(message){
    this.message = message;
}
//然后重点在这里！我们在这里将我们分解的拼接起来。
var Order = function(name){
    var object = new Car();
    object.client  = new Client(name);
    object.feedBack = new FeedBack(object.brand);
    return object;
}


var orderCar = new Order('Vendar-MH');
console.log('The' + orderCar.client.name + '先生、下单一辆' + orderCar.color + '的' + orderCar.brand +' 留言内容 : ' +orderCar.client.message );
orderCar.client.changeMessage('请马上电话联系我')
console.log('The' + orderCar.client.name + '先生、下单一辆' + orderCar.color + '的' + orderCar.brand +' 留言内容 : ' +orderCar.client.message );

----------执行结果--------

TheVendar-MH先生、下单一辆yellow的Tesla 留言内容 : 无留言
TheVendar-MH先生、下单一辆yellow的Tesla 留言内容 : 请马上电话联系我

```
好了,就算是关于这个订单的更加复杂的需求，或者修改需求，不管我们多少各功能在用，我们只要微微一笑，修改下`prototype`等实现就好了0.0




如果您觉得还算不错可以关注我持续看我的文章，大概方向：`前后端语言设计模式`、`如何设计好一款框架`、`源码导读`、`技术实践`。

- 青年才俊可以入群交流:`147255248`


