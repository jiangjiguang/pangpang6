package com.pangpang6.books.guava.primitives;

import com.google.common.primitives.Primitives;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class PrimitivesTest {
    @Test
    public void wrapTest(){
        Class<Integer> integerClass = Primitives.wrap(int.class);
        System.out.println(integerClass.getName());
    }
}
