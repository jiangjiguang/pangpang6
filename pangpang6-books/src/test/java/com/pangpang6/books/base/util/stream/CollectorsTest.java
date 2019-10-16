package com.pangpang6.books.base.util.stream;

import com.pangpang6.utils.MyJSONMapper;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jiangjiguang on 2018/1/6.
 */
public class CollectorsTest {
    @Test
    public void averagingDouble() {
        Stream<String> s = Stream.of("1", "2", "3");
        double o = s.collect(Collectors.averagingDouble(n -> Double.parseDouble(n)));
        System.out.println(o);
    }


    @Test
    public void countingTest() {
        Stream<String> s = Stream.of("1", "2", "3");
        long o = s.collect(Collectors.counting());
        System.out.println(o);
    }

    @Test
    public void groupingByTest() {
        Map o = Employee.persons()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDob()));
        System.out.println(o);
        /*
        Map<Employee.Gender, Long> countByGender = Employee.persons()
                .stream()
                .collect(Collectors.groupingBy(e -> e.);
        System.out.println(countByGender);
        */
    }


}
