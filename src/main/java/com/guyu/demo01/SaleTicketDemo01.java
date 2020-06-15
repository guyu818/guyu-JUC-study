package com.guyu.demo01;
/**
 * @Des 基本的买票例子，传统方式synchronized保证线程安全
 * @Author guyu
 * @Date 2020/4/13 11:36
 * @Param
 * @Return
 */

/**
 * 真正的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 1.属性、方法
 *
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多个线程操作同一资源，把资源丢入线程
        SaleTicket ticket = new SaleTicket();

        //函数式接口jdk1.8 lambda表达式（参数）->{代码}
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }

        },"小王").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"小谷").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"小张").start();
    }
}
//资源类 符合oop面向对象编程 实现接口的话增加了耦合度，所以要才用这种方式
class SaleTicket{
    //属性、方法
    private  int ticket=50;
    //卖票的方式
    //synchronized本质就是排队、锁
    public synchronized  void sale(){
        if (ticket>0) {
            System.out.println(Thread.currentThread().getName()+"买了第"+(ticket--)+"张，还剩"+ticket+"张");
        }
    }

}
