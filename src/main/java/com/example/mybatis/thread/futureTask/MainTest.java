package com.example.mybatis.thread.futureTask;

import java.util.concurrent.*;

public class MainTest {

    private static ExecutorService pool;

    public static void main(String[] args){
        pool = new ThreadPoolExecutor(3,5,1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0;i < 3; i ++) {
            pool.execute(new Thread());
        }

    }

}
