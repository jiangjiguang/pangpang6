package com.pangpang6.books.base.lambda;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jiangjiguang on 2017/12/30.
 */
public class PredicateTest {
    @Test
    public void test() {
        Predicate<Integer> predicate1 = (t) -> t > 3;
        boolean r = predicate1.test(2);
        System.out.println(r);
    }

    @Test
    public void negate(){
        Predicate<Integer> predicate1 = (t) -> t > 3;
        boolean r2 = predicate1.negate().test(2);
        System.out.println(r2);
    }

    @Test
    public void and(){
        Predicate<Integer> p0 = (Integer t) -> t>3;
        Predicate<Integer> p1 = (t) -> t > 3;
        Predicate<Object> p2 = (t) -> Ints.tryParse(t.toString()) < 6;

        Predicate<Integer> p3 = p1.and(p2);
        boolean r = p3.test(5);
        System.out.println(r);
    }

    @Test
    public void filterTest(){
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = Arrays.asList("xxx","yyy","zzz", "");
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            } }
        return results;
    }
}
