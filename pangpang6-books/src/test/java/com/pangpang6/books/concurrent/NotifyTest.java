package com.pangpang6.books.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class NotifyTest {
    private Object lock = new Object();
    private volatile boolean flag = false;


    @Test
    public void test01() throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable("task01"));
        Thread t2 = new Thread(new MyRunnable("task02"));
        Thread t3 = new Thread(new MyRunnable("task03"));
        Thread t4 = new Thread(new MyRunnable("task04"));


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        TimeUnit.SECONDS.sleep(1);

        flag = true;
        notify02();

        TimeUnit.SECONDS.sleep(5);
    }

    private void notify01() {
        synchronized (lock) {
            lock.notify();
        }
    }

    private void notify02() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (!flag) {
                synchronized (lock) {
                    System.out.println(String.format("%s: start", name));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s: end", name));
                }
            }

        }
    }
}
