    package com.guyu.function;

    import java.util.function.Function;

    /**
     * Function函数式接口，有一个输入参数，有一个输出参数
     * 只要是函数型接口，就可以用lambda表达式简化
     */
    public class Demo01 {
        public static void main(String[] args) {
            //Function源码，源码显示需要一个输入值和一个输出值
            /*@FunctionalInterface
            public interface Function<T, R> {
                R apply(T t);*/
            //根据源码，模仿自定义函数式接口
            Function function=new Function<String,String>() {
                @Override
                public String apply(String str) {
                    return str;
                }
            };
            //调用测试
            System.out.println(function.apply("谷大雨"));
            //也可以玩lambda表达式
            Function<String,String> function1=(str)->{return str;};
            System.out.println(function1.apply("谷大雨2"));


        }
    }
