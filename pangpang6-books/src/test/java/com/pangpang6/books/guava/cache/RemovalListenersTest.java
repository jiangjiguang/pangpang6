package com.pangpang6.books.guava.cache;

import com.google.common.cache.RemovalListeners;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class RemovalListenersTest {
    @Test
    public void test(){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new MyThread());

        //RemovalListeners.asynchronous()
    }

    class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("myThread");
        }
    }
}
