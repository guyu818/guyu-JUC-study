package com.guyu.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/*
同步队列
和其他的blockingqueue不一样，synchronousQueue不存储元素
put一个元素，必须先take出来，否则不能put进去值。
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue=new SynchronousQueue();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                synchronousQueue.put("1");//存
                System.out.println(Thread.currentThread().getName()+"put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T1").start();
        new Thread(()->{
            try {
                //take()取值
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T2").start();
    }
}
