package com.guyu.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

//volatile不保证原子性
public class VDemo02 {
    //volatile不保证原子性
    //原子类 Integer
//    private volatile static int num=0;
    private volatile static AtomicInteger num=new AtomicInteger();//不给值初始化为0

    private static void add(){
//        num++;//不是原子性的操作
        num.getAndIncrement();//AtomicInteger 对应的+1方法 使用的CAS 效率极高，比加锁效率高
    }
    public static void main(String[] args) {
        //理论上num应该是2万
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){//main gc
            Thread.yield();//确保充分存活
        }

        System.out.println(num);
    }
}
