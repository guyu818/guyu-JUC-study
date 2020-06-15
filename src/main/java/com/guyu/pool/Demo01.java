package com.guyu.pool;

import java.util.concurrent.*;

/**
 * @Des 线程池的7个参数
 * @Author guyu
 * @Date 2020/4/14 18:18
 * @Param 
 * @Return 
 */
//Executors和collections差不多，工具类
/*
拒绝策略
1.ThreadPoolExecutor.AbortPolicy());//策略1，银行满了，还有人进来，不处理这个人的，抛出异常
2.CallerRunsPolicy());//策略2，哪来的去哪里！结果显示main ok,说明时来自主线程，让他回主线程了
3.DiscardPolicy());//策略3，队列满了，任务丢掉，不抛出异常
4.DiscardOldestPolicy());//策略4，队列满了，尝试和最早的竞争，也不会抛出异常！
 */
public class Demo01 {
    public static void main(String[] args) {
        //自定义线程池
        //超出时抛出异常 RejectedExecutionException

//        1、CPU密集型: 电脑是几核的就设置为几，如何获取CPU是多少核的，可以查看电脑设备×（运维会打死你）
//        整体的方式通过程序来获取》Runtime.getRuntime().availableProcessors()
//        2.IO密集型 设置为大于大于你程序中什么耗IO的线程数即可
//        如程序中 15个大型任务 io十分耗资源，那你就可以设置25个

        //获取cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadpPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());//策略4，队列满了，尝试和最早的竞争，也不会抛出异常！
        try {
            //最大承载：queue+max=3+5=8
            for (int i = 0; i <9; i++) {
                //使用了线程池之后，使用线程池来创建线程
                threadpPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池使用完之后关闭线程池
            threadpPool.shutdown();
        }

    }
}
