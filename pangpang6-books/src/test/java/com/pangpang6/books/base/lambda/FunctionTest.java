package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class FunctionTest {

    @Test
    public void applyTest() {
        List<Integer> r = map(Arrays.asList("lambdas", "in", "action"),
                (String s) -> s.length()
        );

        System.out.println(r);
    }

    @Test
    public void identityTest() {
        Function<Integer, Integer> f2 = Function.identity();
        Integer r2 = f2.apply(1234);
        System.out.println(r2);
    }

    @Test
    public void andThenTest() {
        Function<String, Integer> f1 = (String s) -> s.length();
        Function<Integer, Boolean> f2 = (Integer i) -> i > 3;

        Function<String, Boolean> f3 = f1.andThen(f2);

        Boolean b = f3.apply("11");
        System.out.println(b);
    }


    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }
}
