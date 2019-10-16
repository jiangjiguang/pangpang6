package com.pangpang6.books.reference;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by jiangjiguang on 2017/11/20.
 */
public class SoftRefTest {
    ReferenceQueue<MyObject> softQueue = null;

    public class CheckRefQueue extends Thread {

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            while (true) {
                if (softQueue != null) {
                    Reference<MyObject> obj = null;
                    try {
                        obj = (Reference<MyObject>) softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null){
                        System.out.println("Object for SoftReference is " + obj.get());
                    }

                }
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        MyObject obj = new MyObject();
        softQueue = new ReferenceQueue<MyObject>();
        SoftReference<MyObject> softRef = new SoftReference<MyObject>(obj, softQueue);

        new SoftRefTest.CheckRefQueue().start();
        //去除强引用
        obj = null;
        System.gc();
        System.out.println("After GC:Soft Get= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[4 * 1024 * 800];
        System.out.println("After new byte[]:Soft Get= " + softRef.get());
    }

    @Test
    public void testSimple() throws InterruptedException {
        MyObject obj = new MyObject();

        SoftReference<MyObject> softRef = new SoftReference<MyObject>(obj);
        //去除强引用
        obj = null;
        System.out.println("Soft Get: " + softRef.get());
        System.gc();
        System.out.println("Soft Get: " + softRef.get());
        byte[] b = new byte[4 * 1024 * 925];
        System.out.println("Soft Get: " + softRef.get());
    }

}
