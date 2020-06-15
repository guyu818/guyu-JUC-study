package com.guyu.lock;

import java.util.concurrent.atomic.AtomicReference;

/*
自定义自旋锁
 */
public class SpinLock {

    //thread 初始化为null

    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    //加锁
    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>myLock");

        //自旋锁,
        //如果里面时空的那么我就把我的线程丢进去，不是空的就一直等待
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }
    //解锁
    public void myUnLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>myUnLock");
        atomicReference.compareAndSet(thread,null);
    }



}
