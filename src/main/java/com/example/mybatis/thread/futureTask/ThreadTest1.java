package com.example.mybatis.thread.futureTask;

import java.util.concurrent.Callable;

public class ThreadTest1  implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return 3 + 4;
    }
}
