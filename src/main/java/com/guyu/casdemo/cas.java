package com.guyu.casdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//CAS compareAndSet:比较并交换
public class cas {
    //AtomicStampedReference 注意，如果泛型始是一个包装类，注意对象的引用问题

    //正常在业务操时，这里面比较的一般是一个对象
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(1,1);
    public static void main(String[] args) {


        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();//获得版本号
            System.out.println("a1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2=>"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());


        },"a").start();

        //乐观锁原理相同
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println("b1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp+ 1));
            System.out.println("b2=>"+atomicStampedReference.getStamp());

        },"b").start();

    }
}
