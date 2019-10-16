package com.pangpang6.books.guava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Map;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class SplitterTest {

    @Test
    public void fixedLengthTest(){
        Iterable<String> iterable = Splitter.fixedLength(3).limit(4).split("foohhqbarqqquxqux");
        System.out.println(iterable);
    }

    @Test
    public void omitEmptyStringsTest(){
        Iterable<String> iterable = Splitter.on(",").omitEmptyStrings().trimResults().split("ab2d,ds,dd3,ddd4d,,d");
        System.out.println(iterable);

        Iterable<String> iterable2 = Splitter.on(CharMatcher.digit()).omitEmptyStrings().trimResults().split("ab2ddsdd33ddd4dd");
        System.out.println(iterable2);
    }

    @Test
    public void withKeyValueSeparatorTest(){
        Map<String, String> rm =  Splitter.on(",").withKeyValueSeparator(";").split("1;2,2;3");
        System.out.println(rm);
    }

}
