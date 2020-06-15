package com.guyu.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException  并发修改异常
public class ListTest {
    public static void main(String[] args) {

        //并发下ArrayList不安全的
        /**
         * 解决方案
         * 1.可以换成vector，但是这个回答比较傻，傻子都知道直接加synchronized而vector底层就是这么是实现的
         * 比较源码可知，add方法区别就这么一点，并且vector在jdk1就出来了，ArrayList在jdk1.2才出来的
         *2.List<String> list= Collections.synchronizedList(new ArrayList<>());
         * 3.JUC的方法，推荐高大上一点List<String> list= new CopyOnWriteArrayList<>();
         *
         * CopyOnWriteArrayList写入时复制，cow 计算机程序设计领域的一种优化策略
         * 多线程调用的时候，list 读取的时候，固定的，写入的时候复制一份出来（避免覆盖）
         * 在写入时避免覆盖，造成数据问题
         * 读写分离
         * CopyOnWriteArrayList 比 vector 牛逼在哪？ 主要是效率问题，它使用lock加锁，
         *
         */
        //线程不安全
        //List<String> list=new ArrayList<>();
        //1.List<String> list=new Vector<>();
        //2.List<String> list= Collections.synchronizedList(new ArrayList<>());

        List<String> list= new CopyOnWriteArrayList<>();

        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
