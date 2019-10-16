package com.pangpang6.books.guava.math;

import com.google.common.math.DoubleMath;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class DoubleMathTest {
    @Test
    public void factorialTest(){
        System.out.println(DoubleMath.factorial(4));
    }

    @Test
    public void fuzzyCompareTest(){
        int r = DoubleMath.fuzzyCompare(40,5,40);
        System.out.println(r);
    }

    @Test
    public void fuzzyEqualsTest(){
        System.out.println(DoubleMath.fuzzyEquals(2,3,0));
    }
}
