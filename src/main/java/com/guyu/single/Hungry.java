package com.guyu.single;

//饿汉式单例
public class Hungry {


    //可能会浪费空间
    private byte[] data1=new byte[1024*1024];
    private byte[] data2=new byte[1024*1024];


    //一定要将构造方法私有
    private Hungry(){
    }
    private final static Hungry HUNGRY=new Hungry();

    private static Hungry getInstance(){
        return HUNGRY;
    }
}
