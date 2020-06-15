package com.guyu.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/*
1.普通循环运算
2.高级一点 fork join
3.最高级 stream并行流运算 代码也是最简洁的
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();//第一种 8977
//        test2();//第二种8680
        test3();//第三种276

    }

    public static void test1(){
        Long sum=0L;
        long start = System.currentTimeMillis();
        for(Long i=0L;i<10_0000_0000;i++){
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"运行时间"+(end-start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);//提交任务
        Long sum=submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"运行时间"+(end-start));
    }
    public static void test3(){
        long start = System.currentTimeMillis();
        //stream并行流 rangeClosed这里范围是（]   parallel代表并行 reduce来返回结果
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"运行时间"+(end-start));
    }
}

