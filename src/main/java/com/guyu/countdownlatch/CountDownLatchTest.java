package com.guyu.countdownlatch;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须要执行任务的时候在使用
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();//减一操作，它的总数是六
            },String.valueOf(i)).start();
        }

        countDownLatch.await();//等计数器归零向下执行，等所有人走完了，才关门，需要抛出中断异常

        System.out.println("关门了");
    }
}
