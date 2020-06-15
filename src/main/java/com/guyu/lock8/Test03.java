package com.guyu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5.将发短信和打电话改成静态方法，只有一个对象时，先发短信还是先打电话？ 1.发短信 2.打电话
 * 6.当有两个对象？也是静态的同步方法，先发短信还是先打电话？ 1.发短信 2.打电话
 * 分析：静态方法，类一加载就有了，锁的的是Class 一个类只有一个Class,不管有多少个对象
 * 所以才会出现先发短信，再打电话。
 */
public class Test03 {
    public static void main(String[] args) {
        Phone3 phone=new Phone3();
        Phone3 phone1=new Phone3();


        new Thread(()->{
            phone.sendSms();
        },"A").start();

        //睡眠1s
        try {
            TimeUnit.SECONDS.sleep(1);//以后休眠就用JUC下面的这个方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone1.call();
        },"B").start();
    }
}
class Phone3{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }

}
