package com.example.mybatis.thread.futureTask;

import java.util.concurrent.Callable;

public class ThreadTest2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(4000);
        return 2+3;
    }
}
