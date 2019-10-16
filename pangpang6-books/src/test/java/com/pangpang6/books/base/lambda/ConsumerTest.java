package com.pangpang6.books.base.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jiangjiguang on 2018/1/2.
 */
public class ConsumerTest {
    @Test
    public void acceptTest(){
        Consumer<Integer> c1 = (Integer i) -> System.out.println(i);
        Consumer<Integer> c2 = (Integer i) -> System.out.println("c2" + i);
        //Consumer<Integer> c3 = c1.andThen(c2);
        Consumer<Integer> c3 = c2.andThen(c1);
        forEach(Arrays.asList(1,2,3,4,5), c3);
    }


    private static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){
            c.accept(i);
        }
    }
}
