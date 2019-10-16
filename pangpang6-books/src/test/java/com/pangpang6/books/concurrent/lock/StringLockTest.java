package com.pangpang6.books.concurrent.lock;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class StringLockTest {

    private static Map<String, FastDateFormat> fastDateFormatMap = new ConcurrentHashMap<String, FastDateFormat>();

        //只要String 是new出来的 那么就不是一把锁
    String lock = "lock";

    CountDownLatch countDownLatch = new CountDownLatch(2);

    @Test
    public void lockTest() throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable(fastDateFormatMap));
        Thread t2 = new Thread(new MyRunnable(fastDateFormatMap));

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(10);
    }

    class MyRunnable implements Runnable {
        Object lockThread;

        public MyRunnable(Object lock) {
            this.lockThread = lock;
        }

        @Override
        public void run() {
            StringLock.lock(lockThread);
        }
    }
}
