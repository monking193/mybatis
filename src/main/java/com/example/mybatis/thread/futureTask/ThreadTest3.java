package com.example.mybatis.thread.futureTask;

import java.util.concurrent.Callable;

public class ThreadTest3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1+1;
    }
}
