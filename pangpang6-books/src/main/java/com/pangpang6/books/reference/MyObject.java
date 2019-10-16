package com.pangpang6.books.reference;

/**
 * Created by jiangjiguang on 2017/11/20.
 */
public class MyObject {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject's finalize called");
    }
    @Override
    public String toString(){
        return "I am MyObject";
    }
}
