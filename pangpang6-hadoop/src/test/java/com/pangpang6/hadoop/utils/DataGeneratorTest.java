package com.pangpang6.hadoop.utils;

import org.junit.Test;

import java.util.Random;

public class DataGeneratorTest {
    private static Random rand = new Random(46474747);


    @Test
    public void test02() {
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextDouble());

        System.out.println(rand.nextDouble());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextDouble());

    }

    @Test
    public void test01() {
        System.out.println(generateRandomString());
    }

    private static String generateRandomString() {
        int leftLimit = 48; // ascii for 0
        int rightLimit = 57; // ascii for 9
        int stringLength = 3;
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (rand.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
