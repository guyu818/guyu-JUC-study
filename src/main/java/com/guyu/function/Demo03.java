package com.guyu.function;


import java.util.function.Consumer;

/**
 * Consumer消费型接口，只有输入，没有返回值
 */
public class Demo03 {
    public static void main(String[] args) {
        //利用消费型接口打印字符串
        Consumer<String> consumer=new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("哈哈哈的骄傲");
        //lambda
        Consumer<String> consumer1=(str)->{ System.out.println(str); };
        consumer1.accept("hhhahhah");
    }
}
