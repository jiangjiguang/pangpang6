package com.pangpang6.books.guava.primitives;

import com.google.common.primitives.Booleans;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/15.
 */
public class BooleansTest {

    @Test
    public void compareTest(){
        int r = Booleans.compare(true, false);
        System.out.println(r);
    }
}
