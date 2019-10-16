package com.pangpang6.books.guava.base;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class PreconditionsTest {
    @Test
    public void checkArgumentTest(){
        Preconditions.checkArgument(false);
    }
}
