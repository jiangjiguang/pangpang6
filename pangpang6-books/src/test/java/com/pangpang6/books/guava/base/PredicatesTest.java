package com.pangpang6.books.guava.base;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class PredicatesTest {
    @Test
    public void alwaysFalseTest(){
        Predicate<String> predicate = Predicates.alwaysFalse();
        System.out.println(predicate.apply(""));
    }

    @Test
    public void andTest(){
        Predicate<String> p1 = Predicates.alwaysTrue();
        Predicate<String> p2 = Predicates.alwaysFalse();

        Predicate<String> p3 = Predicates.and(p1, p2);

        System.out.println(p1.apply(""));
        System.out.println(p2.apply(""));

        System.out.println(p3.apply(""));

    }
}
