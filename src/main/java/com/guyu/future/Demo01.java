package com.guyu.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
异步调用 CompletableFuture
//异步执行
//成功回调
//失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的runAsync 异步回调
//        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//        System.out.println("111111");
//        completableFuture.get();//阻塞获取执行结果
        //有返回值的 supplyAsync 异步回调
        //ajax 成功和失败的回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            int i=10/0;
            return 1024;//返回值
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=》" + t);//正常的返回结果
            System.out.println("u=>" + u);//错误信息java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {//失败的操作
            System.out.println(e.getMessage());//输出错误信息
            return 233;//可以获取到错误的返回结果
        }).get());

        /**
         * succee Code 200
         * error Code 400 500
         */


    }

}
