package com.guyu.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
独占锁 （写锁） 一次只能被一个线程占有
共享锁 （读锁） 可以被多个线程占有
readwritelock
读 读 可以共存
读 写 不能共存
 写写 不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache2 myCache=new MyCache2();

        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");//多线程写
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");//多线程读
            },String.valueOf(i)).start();
        }

    }
}
//自定义缓存
//使用读写锁来实现，写的时候只有一个写，读的时候不限制
class MyCache2{
    private volatile Map<String,Object> map=new HashMap<>();
    //这就是读写锁
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    //存，写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();//上写锁
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();//写锁解除
        }
    }
    //取，读
    public void get(String key){
        readWriteLock.readLock().lock();//上读锁
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();//读锁解锁
        }
    }

}
//这种的是不安全的，没有保证写的时候只有一个线程写
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();

    //存，写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入ok");
    }
    //取，读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取ok");
    }

}