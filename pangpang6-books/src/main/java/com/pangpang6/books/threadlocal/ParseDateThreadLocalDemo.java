package com.pangpang6.books.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/11/20.
 */
public class ParseDateThreadLocalDemo {
    //把非线程安全的变量放到ThreadLocal中, 但是若把相同的实例分到不同的ThreadLocal中，也不能保证线程安全
    //ThreadLocal内部由ThreadLocalMap保存数据，ThreadLocal是线程私有的，每个线程都有一个
    //ThreadLocalMap的key为ThreadLocal对象，value为保存的对象
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date = threadLocal.get().parse("2017-05-06 12:33:" + i % 60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
