package com.pangpang6.utils;

import org.junit.Test;

import java.util.Date;

public class MyDateUtilsTest {

    @Test
    public void test01() {
        String s = "2017-10-30T14:24:54+08:00";
        Date d = MyDateUtils.str2Date(s);

        System.out.println(MyDateUtils.date2StrDefult(d));
    }
}
