package com.pangpang6.books.guava.math;

import com.google.common.math.Quantiles;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjiguang on 2017/12/13.
 */
public class QuantilesTest {
    @Test
    public void medianTest(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(10);

        double d1 = Quantiles.median().compute(list);
        System.out.println(d1);
    }

    @Test
    public void percentilesTest(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(10);
        double d1 = Quantiles.percentiles().index(2).compute(list);
        System.out.println(d1);
    }
}
