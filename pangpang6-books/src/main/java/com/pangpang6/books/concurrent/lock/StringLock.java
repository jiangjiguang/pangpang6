package com.pangpang6.books.concurrent.lock;

import com.pangpang6.utils.MyDateUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StringLock {

    public static void lock(Object lock) {
        System.out.println(String.format("thread: %s, time: %s, prepare lock",
                Thread.currentThread(), MyDateUtils.date2StrDefult(new Date())));
        synchronized (lock) {
            System.out.println(String.format("thread: %s, time: %s, ing lock",
                    Thread.currentThread(), MyDateUtils.date2StrDefult(new Date())));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("thread: %s, time: %s, release lock",
                Thread.currentThread(), MyDateUtils.date2StrDefult(new Date())));
    }
}
