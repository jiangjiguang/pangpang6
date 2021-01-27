package com.pangpang6.hadoop.utils;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void test01() {
        IntStream intStream = IntStream.range(0, 10);
//        intStream.forEach(
//                x -> System.out.println(x)
//        );
        Stream<Integer> stream = intStream.boxed();
        stream.forEach(x -> System.out.println(x));
//        System.out.println(intStream);
//        intStream.boxed().map(x -> {
//            System.out.println(x.intValue());
//            return null;
//        }
//        );
    }

    @Test
    public void test02() {
        List<Widget> widgets = Lists.newArrayList(
                new Widget("RED", 2),
                new Widget("RED", 3),
                new Widget("RED2", 3)
        );

        int a = widgets.stream()
                .filter(b -> b.getColor() == "RED")
                .sorted((x, y) -> x.getWeight() - y.getWeight())
                .mapToInt(Widget::getWeight)
                .sum();
        System.out.println(a);
    }


    class Widget {

        public Widget(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        private String color;
        private int weight;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
