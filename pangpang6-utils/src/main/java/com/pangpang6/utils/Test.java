package com.pangpang6.utils;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by jiangjg on 2017/5/27.
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = (List<String>) Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
    }
}
