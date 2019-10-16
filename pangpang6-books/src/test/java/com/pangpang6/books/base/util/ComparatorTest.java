package com.pangpang6.books.base.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class ComparatorTest {

    @Test
    public void compareTest(){
        Comparator<Integer> c = (Integer s1, Integer s2) -> s1.compareTo(s2);
        List<Integer> list = new ArrayList<>(Arrays.asList(10,2,30,14,5));
        list.sort(c);

        System.out.println(list);
    }

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>(Arrays.asList(10,2,30,14,5));
        System.out.println(list);
        list.sort(Comparator.comparing(Integer::intValue));

        System.out.println(list);
    }
}
