package com.pangpang6.books.base.util.stream;

import com.pangpang6.utils.MyJSONMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by jiangjiguang on 2018/1/3.
 */
public class StreamTest {
    List<Dish> menu = new ArrayList<>();

    @Before
    public void init() {
        menu.add(new Dish("青菜", true, 50, Dish.Type.OTHER));
        menu.add(new Dish("白菜", true, 100, Dish.Type.OTHER));
        menu.add(new Dish("猪肉", false, 310, Dish.Type.MEAT));
        menu.add(new Dish("羊肉", false, 400, Dish.Type.MEAT));
        menu.add(new Dish("牛肉", false, 350, Dish.Type.MEAT));
        menu.add(new Dish("鲈鱼", false, 500, Dish.Type.FISH));
        menu.add(new Dish("莴笋 ", true, 150, Dish.Type.FISH));
    }

    @Test
    public void zhongjiancaozuoTest() {
        List<String> names = menu.stream().filter(
                d -> {
                    System.out.println(String.format("filter: %s", d.getName()));
                    return d.getCalories() > 300;
                }
        ).map(d -> {
            System.out.println(String.format("map: %s", d.getName()));
            return d.getName();
        }).limit(3).collect(toList());

        System.out.println(names);
    }

    @Test
    public void allMatchTest() {
        Predicate<Dish> p1 = d -> {
            System.out.println(d.getName());
            return d.isVegetarian();
        };
        boolean r1 = menu.stream().allMatch(p1);
        System.out.println(r1);

        Predicate<Dish> p2 = d -> d.getCalories() > 10;
        boolean r2 = menu.stream().allMatch(p2);
        System.out.println(r2);
    }

    @Test
    public void anyMatchTest() {
        Predicate<Dish> p1 = d -> {
            System.out.println(d.getName());
            return d.isVegetarian();
        };
        boolean r1 = menu.stream().anyMatch(p1);
        System.out.println(r1);

        Predicate<Dish> p2 = d -> d.getCalories() > 10;
        boolean r2 = menu.stream().anyMatch(p2);
        System.out.println(r2);
    }


    @Test
    public void distinctTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void filterTest() {
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(vegetarianMenu);
    }

    @Test
    public void flatMapTest() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");

        Stream<String> stream = words.stream();
        //stream.forEach(System.out::println);

        List r = stream.map(word -> word.split(""))
                //.forEach(System.out::println);
                .distinct()
                .collect(toList());
        System.out.println(r);

        //结果是数组对象

        //首先需要得到字符流而不是数组流
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        streamOfwords.forEach(System.out::println);


        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .forEach(System.out::println);
        //现在得到的是流的列表Stream<String>

        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .forEach(System.out::println);
        //现在得到的才是字符流

        words.stream()
                .map(word -> word.split(""))
                .flatMap(a -> Arrays.stream(a))
                .forEach(System.out::println);

        //使用flatMap的效果是各个数组并不是分别映射成一个流，而是映射成流的内容
        //就是把流中的每个值映射成一个流，然后把所有的流连接成一个流
    }

    //给定两个数组，返回所有的数对
    //最终要得到的是Stream<Integer[]>
    //
    @Test
    public void test() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());


        System.out.println(MyJSONMapper.nonDefaultMapper().toJSONString(pairs));
    }


    @Test
    public void limitTest() {
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);
    }

    @Test
    public void mapTest() {
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);
    }

    @Test
    public void reduceTest() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        BinaryOperator<Integer> binaryOperator = (Integer s, Integer s2) -> s + s2;
        int sum2 = numbers.stream().reduce(0, binaryOperator);
        System.out.println(sum2);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max);
    }

    //数值流的应用：勾股数
    @Test
    public void a1Test() {
        System.out.println(Math.sqrt(2 * 2 + 3 * 3) % 1);

        int a1 = 3;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a1 * a1 + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a1, b, (int) Math.sqrt(a1 * a1 + b * b)})
                .forEach(a -> System.out.println(MyJSONMapper.nonDefaultMapper().toJSONString(a)));

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );


        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                        .filter(t -> t[2] % 1 == 0));

    }

    @Test
    public void createTest(){
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
    }

    @Test
    public void iterateTest(){
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }


}
