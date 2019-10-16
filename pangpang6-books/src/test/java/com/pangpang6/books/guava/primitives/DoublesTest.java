package com.pangpang6.books.guava.primitives;

import org.junit.Test;
import com.google.common.primitives.Doubles;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class DoublesTest {
    @Test
    public void compareTest(){
        int r = Doubles.compare(2.0, 3.3);
        System.out.println(r);
    }

    @Test
    public void containsTest(){
        double[] dd = new double[4];
        dd[0] = 2.00;
        boolean b = Doubles.contains(dd, 2.0);
        System.out.println(b);
    }

    @Test
    public void tryParseTest(){
        Double d = Doubles.tryParse("3");
        System.out.println(d);
    }
}
