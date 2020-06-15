package com.guyu.function;

import java.util.function.Supplier;

/**
 * Supplier接口，没有参数，只有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier supplier=new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };
        System.out.println(supplier.get());
        Supplier<String> supplier1=()->{ return "谷大雨"; };

        System.out.println(supplier1.get());
    }
}
