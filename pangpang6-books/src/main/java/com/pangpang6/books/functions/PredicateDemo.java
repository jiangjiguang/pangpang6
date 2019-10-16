package com.pangpang6.books.functions;

import java.util.function.Predicate;

/**
 * Created by jiangjiguang on 2017/12/10.
 */
public class PredicateDemo {
    public static void main(String[] args) {
        test();
    }


    private static void test() {
        Predicate<Integer> p1 = x -> x > 5;
        Predicate<Integer> p2 = x -> x > 50;
        Predicate<Integer> p3 = p1.or(p2).and(p2).or(p2);
        System.out.println(p3.test(6));

        Predicate<Character> p4 = x -> Character.isDigit(x);
    }
}
