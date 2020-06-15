package com.guyu.tvolatile;

/*
线程之间没有通信，导致一直运行出现问题
 */
import java.util.concurrent.TimeUnit;

public class JMMDemo {
    //加上volatile保证了可见性
    static volatile  int num=0;
    public static void main(String[] args) {
        new Thread(()->{//线程1不知道 主线程已经修改了
            while (num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);//先休息1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num=1;

        System.out.println(num);
    }
}
