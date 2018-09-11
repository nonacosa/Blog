package com.guava.common.base;

import java.util.function.Function;

/**
 * Guava com.google.common.base.Function
 * 原始的Function实现 - Guava
 *
 * 使用函数式编程的条件：
 * 1 使用函数式风格以后，整个工程的代码行会净减少
 * 2 为了提高效率，转换集合的结果需要懒视图，而不是明确计算过的集合，真正做了性能测试并且有测试数据来证明函数式版本更快
 */
public class Function_ {

  public static void main(String[] args) {
    jdk8Function();
  }


  public static void jdk8Function(){
    Function<Integer, Integer> times2 = i -> i*2;
    /**
     * 简写
     */
    Function<Integer, Integer> squared =new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer i) {
        return i*i;
      }
    };

    System.out.println(times2.apply(4));
    System.out.println(squared.apply(4));

    System.out.println(times2.compose(squared).apply(4));  //32                先4×4然后16×2,先执行apply(4)，在times2的apply(16),先执行参数，再执行调用者。
    System.out.println(times2.andThen(squared).apply(4));  //64               先4×2,然后8×8,先执行times2的函数，在执行squared的函数。

    System.out.println(Function.identity().compose(squared).apply(4));   //16
  }

  public static void guavaFunction() {

  }


}
