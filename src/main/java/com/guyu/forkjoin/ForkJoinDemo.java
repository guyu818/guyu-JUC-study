package com.guyu.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 如何使用forkjoin
 * 1.通过forkjoinpool来执行
 * 2.计算任务forkjoinpool.execute(ForkJoinTask task)
 * 3.计算类继承ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start; //起始值
    private Long end;   //结束值

    private Long temp=10000L;  //临界值

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    //计算方法必须继承实现
    @Override
    protected Long compute() {
        if((end-start)<temp){
            Long sum=0L;
            for(Long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else{//forkjoin 递归思想
            long middle=(end+start)/2;//中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join()+task2.join();
        }

    }
}
