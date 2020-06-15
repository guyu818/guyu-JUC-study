package com.guyu.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @Des conditon实现精准唤醒
 * @Author guyu
 * @Date 2020/4/13 16:32
 * @Param
 * @Return
 */

/**
 * A执行完调用B,B执行完了调用C,C执行完调用A
 * 实际应用;生产线  下单-》支付—》交易—》物流
 */
public class C  {
    public static void main(String[] args) {
        Data3 data3=new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
    }
}
class Data3{
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();
    private int num=1;   //1A 2B 3C

    public void printA(){
        try {
            lock.lock();
            //业务
            while (num!=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"AAAAAAAAAAA");
            num=2;
            condition2.signal();//唤醒B
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }
    public void printB(){
        try {
            lock.lock();
            //业务
            while (num!=2){
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"BBBBBBB");
            num=3;
            condition3.signal();//唤醒B
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }

    }
    public void printC(){
        try {
            lock.lock();
            //业务
            while (num!=3){
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"CCCCCCCCC");
            num=1;
            condition1.signal();//唤醒B
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }

    }

}
