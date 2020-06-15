package com.guyu.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Des 8锁，就是关于锁的8个问题
 * @Author guyu
 * @Date 2020/4/13 17:30
 * @Param 
 * @Return 
 */

/**
 * 1.标准情况下，两个线程先打印 发短信还是打电话？——》1.发短信 2 .打电话
 * 2.发短信延迟4s,在测试事发短信还是打电话？——》1.发短信 2.打电话
 * 原因解析：synchronized会锁住方法的调用者，也就是我们实例化的这个phone对象，
 * 所以发短信执行完才会释放，打电话才会执行
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone=new Phone();

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
class Phone{
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

}
