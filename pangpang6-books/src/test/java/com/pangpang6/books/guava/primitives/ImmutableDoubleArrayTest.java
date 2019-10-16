package com.pangpang6.books.guava.primitives;

import com.google.common.primitives.ImmutableDoubleArray;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class ImmutableDoubleArrayTest {
    @Test
    public void buildTest(){
        double[] dd = new double[4];
        dd[0] = 1.1;
        ImmutableDoubleArray array = ImmutableDoubleArray.builder().addAll(dd).build();
        System.out.println(array.stream().count());
    }
}
