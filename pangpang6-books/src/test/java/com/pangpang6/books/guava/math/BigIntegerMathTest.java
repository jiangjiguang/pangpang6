package com.pangpang6.books.guava.math;

import com.google.common.math.BigIntegerMath;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class BigIntegerMathTest {
    @Test
    public void binomialTest(){
        BigInteger b1 = BigIntegerMath.binomial(5,3);
        System.out.println(b1.intValue());
    }

    @Test
    public void ceilingPowerOfTwoTest(){
        BigInteger b1 = BigIntegerMath.ceilingPowerOfTwo(BigInteger.valueOf(5));
        System.out.println(b1.intValue());
    }
}
