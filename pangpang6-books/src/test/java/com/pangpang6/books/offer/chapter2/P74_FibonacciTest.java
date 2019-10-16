package com.pangpang6.books.offer.chapter2;

import org.junit.Test;

public class P74_FibonacciTest {

    @Test
    public void fibonacci1Test() {
        int result = P74_Fibonacci.fibonacci1(3);
        System.out.println(result);
    }

    @Test
    public void fibonacci2Test() {
        int result = P74_Fibonacci.fibonacci2(4);
        System.out.println(result);
    }
}
