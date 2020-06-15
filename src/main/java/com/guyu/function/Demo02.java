package com.guyu.function;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 */
public class Demo02 {
    public static void main(String[] args) {
        //判断字符串是否为空
        Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        System.out.println(predicate.test(""));
        //同理可以lambda表达式
        Predicate<String> predicate1=(str)->{return str.isEmpty();};
        System.out.println(predicate1.test(""));
    }
}
