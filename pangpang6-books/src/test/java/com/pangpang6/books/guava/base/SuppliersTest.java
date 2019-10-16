package com.pangpang6.books.guava.base;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class SuppliersTest {
    @Test
    public void composeTest(){
        Function<Integer, Integer> f1 = x -> x + 1;
        Supplier<Integer> s1 = () -> 4;
        Supplier<Integer> s2 = Suppliers.compose(f1, s1);
        System.out.println(s2.get());
    }

    @Test
    public void memoizeWithExpirationTest(){
        Supplier<Integer> s1 = () -> {
            System.out.println("-----------");
            return 4;
        };
        Supplier<Integer> s2 = Suppliers.memoizeWithExpiration(s1, 10, TimeUnit.SECONDS);
        System.out.println(s2.get());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s2.get());
    }
}
