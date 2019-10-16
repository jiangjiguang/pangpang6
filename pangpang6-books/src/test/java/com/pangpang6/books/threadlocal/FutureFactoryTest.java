package com.pangpang6.books.threadlocal;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jiangjiguang on 2017/11/21.
 */
public class FutureFactoryTest {
    private String get(){
        return "hello world";
    }

    @Test
    public void getFutureTest(){
        this.get();
        Future future = FutureFactory.getFuture();
        try {
            String ss = (String) future.get();
            System.out.println(ss);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
