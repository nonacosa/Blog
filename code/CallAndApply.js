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
peaple.say.apply(Peaple1);//小强   this被指向了  |  为了动态改变 this 而出现的
//Fn1并没有say这个方法,但是fn原型有,那么fn.call。。。
//具体差距呢↓
接受参数不一样

call()方法中的其余的参数必须直接传给函数

apply()接收两个参数：一个参数是运行时的作用域,另一个是参数数组、或arguments


