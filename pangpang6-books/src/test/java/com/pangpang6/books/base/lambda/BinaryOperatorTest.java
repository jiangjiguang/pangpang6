package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class BinaryOperatorTest {
    @Test
    public void applyTest() {
        BinaryOperator<String> b = (String s, String s2) -> s + s2;
        System.out.println(b.apply("222", "333"));
    }

    @Test
    public void maxByTest(){
        Comparator<Integer> c = (Integer s1, Integer s2) -> s1.compareTo(s2);
        BinaryOperator<Integer> b = BinaryOperator.maxBy(c);
        int r1 = b.apply(10,100);
        System.out.println(r1);
    }

    @Test
    public void minByTest(){
        BinaryOperator<Integer> bi = BinaryOperator.minBy(Comparator.naturalOrder());
        System.out.println(bi.apply(2, 3));
    }
}
