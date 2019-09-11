package com.example.mybatis.thread.threadPool;

import java.util.concurrent.*;

public class ThreadPool {
    private static ExecutorService pool;

    public static void main( String[] args ) {
        //自定义拒绝策略
        pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + "执行了拒绝策略");

            }
        });


    }

}
