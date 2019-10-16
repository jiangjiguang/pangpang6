package com.pangpang6.books.base;

import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by jiangjiguang on 2017/9/1.
 */
//泛型
public class GenericityDemo {
    public static void main(String[] args) {
        //genericity();
        //test();
        //arrayTest();
        //upperG();
        downG();
    }

    //泛型类型的copy
    public static <T> void copy2(List<? super T> dest, List<? extends T> src)
    {
        for (int i=0; i<src.size(); i++)
            dest.set(i,src.get(i));
    }


    public static <Animal> void copy(List<? super Animal> dest, List<? extends Animal> src)
    {
        for (int i=0; i<src.size(); i++)
            dest.set(i,src.get(i));
    }

    //下边界限定符 ? super T，T 是类型参数的下界
    //使用这种形式的通配符，我们就可以 ”传递对象”
    private static void downG(){
        List<? super Dog> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new BlackDog());
        //list.add(new Animal());  //错误

        //Dog animal = list.get(0);  //错误

    }

    //上边界通配符 <? extends Animal>
    private static void upperG(){
        List<? extends Animal> list = new ArrayList<>();
        //list.add(new Animal());  上边界通配符，不允许添加除null之外的任何对象
        list.add(null);


        Animal dog = list.get(0);   //上边界通配符可以使用get

        List<? extends Animal> list2 = Arrays.asList(new Dog(), new Cat());

        Animal animal = list2.get(0);
        Animal animal1 = list2.get(1);
        System.out.println(animal);
        System.out.println(animal1);

    }


    //数组的协变
    private static void arrayTest(){
        Animal[] animals = new Dog[10];

        animals[0] = new Dog();
        animals[1] = new BlackDog();
        //animals[2] = new Animal();  //异常
        //animals[3] = new Cat();       //异常

        System.out.println(animals);
    }

    private static void test(){
        //List<Animal> list = new ArrayList<Dog>();  //错误， 无法编译   泛型不支持协变
        List<Animal> list = new ArrayList<>();

        list.add(new Dog());
        list.add(new Cat());
        System.out.println(list);
    }

    private static void genericity(){
        List<Object> list = new ArrayList();

        list.add(new Integer(1));
        list.add(new String("111"));

        System.out.println(list);
    }
}
