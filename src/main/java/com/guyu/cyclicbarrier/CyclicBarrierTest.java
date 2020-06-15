package com.guyu.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//加法器
public class CyclicBarrierTest {
    public static void main(String[] args) {
        /*
        集齐7颗龙珠召唤神龙
         */
        //召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <= 7; i++) {
            //注意点，lambda表达里面可以操作变量i嘛？
            //不能！因为lambda表达式是创建了一个类，但是可以将i变为final类型的
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
