package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class DoubleUnaryOperatorTest {
    @Test
    public void applyAsDoubleTest() {
        DoubleUnaryOperator dl = (x) -> {
            return x * x;
        };
        System.out.println(dl.applyAsDouble(3.14));
    }

    @Test
    public void andThenTest(){
        DoubleUnaryOperator square = (x) -> {return x*x;};

        DoubleUnaryOperator doubleValue = (x) -> {return 2*x;};

        System.out.println(doubleValue.andThen(square).applyAsDouble(3.14));
    }
}
