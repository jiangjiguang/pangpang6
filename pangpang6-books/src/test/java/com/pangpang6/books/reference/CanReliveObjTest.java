package com.pangpang6.books.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;

/**
 * Created by jiangjiguang on 2017/11/20.
 */

/**
 * 虚引用只是一个代替finalize方法的方式，但是并不能回收在这个方法中重新强引用
 */
public class CanReliveObjTest {
    ReferenceQueue<CanReliveObj> phantomQueue = null;

    public class CheckRefQueue extends Thread {

        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    Reference<CanReliveObj> obj;
                    try {
                        obj = (Reference<CanReliveObj>) phantomQueue.remove();
                        System.out.println("clean resouce:" + obj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    @Test
    public void test() throws InterruptedException {
        CanReliveObj obj = new CanReliveObj();
        phantomQueue = new ReferenceQueue<CanReliveObj>();
        PhantomReference<CanReliveObj> phantomRef = new PhantomReference<CanReliveObj>(obj, phantomQueue);
        new CanReliveObjTest.CheckRefQueue().start();
        obj = null;
        Thread.sleep(1000);
        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            Thread.sleep(1000);
        }
    }
}
