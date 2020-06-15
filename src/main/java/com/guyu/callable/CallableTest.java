package com.guyu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread().start();//怎么启动callable呢
        //******下面是推导过程
        //Thread只能接受Runnable
        //new Thread(new Runnable()).start();
        //下面开始找到了Runnable有关系的实现类 new FutureTask<>()
        //new Thread(new FutureTask<>()).start();
        //这时候因为FutureTask<>()它的构造方法可以接受callable，所以可以使用FutureTask来实现
        //new Thread(new FutureTask<>( Callable )).start();
        MyThread myThread=new MyThread();
        FutureTask futureTask=new FutureTask(myThread);//相当于适配类

        new Thread(futureTask,"A").start();//启动
        new Thread(futureTask,"B").start();//结果会被缓存效率高
        //这里会打印一个call

        String o = (String) futureTask.get();//获取返回值，同时需要捕获异常,
        // 这个get方法可能会产生阻塞，一般把它放到最后或者使用异步通信
        System.out.println(o);


    }
}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("我是call()");
        //耗时的操作
        return "558d44dad";
    }
}
