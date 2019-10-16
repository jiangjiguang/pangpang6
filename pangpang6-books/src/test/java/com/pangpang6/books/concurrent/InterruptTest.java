package com.pangpang6.books.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class InterruptTest {


    @Test
    public void test01() throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable("test01"), "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(3);

//        boolean isInterrupted = t1.isInterrupted();
//        System.out.println("isInterrupted(1): " + isInterrupted);
//        t1.interrupt();
//        isInterrupted = t1.isInterrupted();
//        System.out.println("isInterrupted(2): " + isInterrupted);
//        TimeUnit.SECONDS.sleep(2);
//        isInterrupted = t1.isInterrupted();
//        System.out.println("isInterrupted(3): " + isInterrupted);

        TimeUnit.SECONDS.sleep(5);
    }


    class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s--%s--start", name, Thread.currentThread().getName()));

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Thread.currentThread().interrupt();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException : " + Thread.currentThread().isInterrupted());
                }
            }

            System.out.println(String.format("%s--%s--end", name, Thread.currentThread().getName()));
        }
    }
}
