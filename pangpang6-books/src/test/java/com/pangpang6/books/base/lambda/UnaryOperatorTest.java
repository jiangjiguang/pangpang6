package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.UnaryOperator;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class UnaryOperatorTest {

    @Test
    public void identityTest(){
        UnaryOperator<String> i  = (x)-> x.toUpperCase();

        System.out.println(i.apply("test1234"));
    }
}
