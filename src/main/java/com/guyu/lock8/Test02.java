package com.guyu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3.新增加了一个普通方法之后，是先打印发短信还是hello？ 1.hello 2.发短信
 * 分析：普通方法没锁，不受锁的影响
 *4.两个对象的情况下是先发短信还是先打电话？ 1.打电话 2.发短信
 * 分析：两个对象两把锁，互相不影响，但是发短信我们设置了延迟，所以会先打电话
 */

public class Test02 {
    public static void main(String[] args) {
        Phone2 phone=new Phone2();
        Phone2 phone1=new Phone2();

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
class Phone2{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }

    //新加了一个方法
    //这里没有锁，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }

}