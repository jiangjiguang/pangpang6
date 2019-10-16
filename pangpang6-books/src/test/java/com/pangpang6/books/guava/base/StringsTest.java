package com.pangpang6.books.guava.base;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class StringsTest {
    @Test
    public void commonPrefixTest(){
        String ss = Strings.commonPrefix("asa", "asssss");
        System.out.println(ss);
    }

    @Test
    public void commonSuffixTest(){
        String ss = Strings.commonSuffix("asa", "asssssa");
        System.out.println(ss);
    }

    @Test
    public void baseTest(){
        System.out.println(Strings.emptyToNull("11"));
    }
}
