package com.guyu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7. 一个静态同步方法，一个普通的同步方法，一个对象，先打印那个？ 1.打电话 2.发短信
 * 8.一个静态同步方法，一个普通的同步方法，两个对象，先打印那个？ 1.打电话 2.发短信
 * 分析，这两个锁的东西不一样，静态的那个锁，锁的是Class类模板
 * 而普通的那个锁的是 对象，他们其实是不影响的，因此先打电话
 */
public class Test04 {
    public static void main(String[] args) {
        Phone4 phone=new Phone4();
        Phone4 phone1=new Phone4();


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
            phone.call();
        },"B").start();
    }
}
class Phone4{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public  synchronized void call(){
        System.out.println("打电话");
    }

}
