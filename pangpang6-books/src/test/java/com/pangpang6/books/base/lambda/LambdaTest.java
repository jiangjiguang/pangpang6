package com.pangpang6.books.base.lambda;

import org.junit.Test;

/**
 * Created by jiangjiguang on 2018/1/2.
 * http://www.java2s.com/Tutorials/Java/java.util.function/UnaryOperator/index.htm
 */
public class LambdaTest {
    @Test
    public void test01(){
        Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable(){
            public void run(){
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
    }

    private static void process(Runnable r){
        r.run();
    }
}
