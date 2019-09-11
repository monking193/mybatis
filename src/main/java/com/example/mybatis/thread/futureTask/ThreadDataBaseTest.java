package com.example.mybatis.thread.futureTask;

import java.util.concurrent.*;

public class ThreadDataBaseTest {

    private static ExecutorService pool;
    public static void main( String[] args ) throws Exception{
        long start = System.currentTimeMillis();
        pool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS,
                    new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        Future<Integer> a = pool.submit(new ThreadTest1());
        Future<Integer> b = pool.submit(new ThreadTest2());
        Future<Integer> c = pool.submit(new ThreadTest3());

        pool.shutdown();
        while (true) {
            if (pool.isTerminated()){
                int d = a.get() + b.get() + c.get();
                System.out.print(d);
                break;
            }
        }
        System.out.print("cost time:" + (System.currentTimeMillis() - start));

    }


}
