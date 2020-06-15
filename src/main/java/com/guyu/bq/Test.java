package com.guyu.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test3();
    }
    /*
    抛出异常
     */
    public static void test1(){
        //队列的大小
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());
        System.out.println("==================");
        //java.lang.IllegalStateException: Queue full 抛出异常，队列满了
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //java.util.NoSuchElementException 抛出异常没有元素
        System.out.println(blockingQueue.remove());
    }
    /*
    有返回值不抛异常
     */
    public static void test2(){
        //队列长度为3
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//不抛异常，返回false
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//返回为null
    }
    /*
    阻塞等待，等到死
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
        System.out.println("=========");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //死等现象，程序不结束
    }
    /*
    超时等待 超过时间就不等了
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue=new ArrayBlockingQueue(3);

        blockingQueue.offer("a");
        blockingQueue.offer("c");
        blockingQueue.offer("b");

        blockingQueue.offer("d",2, TimeUnit.SECONDS);//等待两秒超时退出
        System.out.println("==============");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll(2,TimeUnit.SECONDS);//超2秒退出

    }

}
