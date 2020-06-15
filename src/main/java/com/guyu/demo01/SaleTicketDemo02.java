package com.guyu.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Des 基本的买票例子，JUC下的lock保证线程安全
 * @Author guyu
 * @Date 2020/4/13 11:36
 * @Param
 * @Return
 */

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发：多个线程操作同一资源，把资源丢入线程
        SaleTicket1 ticket = new SaleTicket1();

        //函数式接口jdk1.8 lambda表达式（参数）->{代码}
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"小王").start();
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"小谷").start();
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"小张").start();

    }
}

//lock三部曲
//1.Lock lock=new ReentrantLock();
//2.lock.lock();//加锁
//3.finally-->lock.unlock();//解锁
class SaleTicket1{

    //属性、方法
    private  int ticket=50;

    Lock lock=new ReentrantLock();

    //卖票的方式
    public void sale(){
        lock.lock();//加锁

        try {
            if (ticket>0) {
                System.out.println(Thread.currentThread().getName()+"买了第"+(ticket--)+"张，还剩"+ticket+"张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }

}
