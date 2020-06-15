package com.guyu.demo01;

public class Test01 {
    public static void main(String[] args) {
        //获取CPU的核数
//        ｄａｄａｄａ出现这种情况，是圆半角问题，去语言栏，设置按键，开启shift+空格就可切换
        //下面会讲到CPU密集和I/O密集
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
