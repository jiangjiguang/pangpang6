package com.pangpang6.books.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2018/2/6.
 */
public class ThreadPoolExecutorTest {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

}
