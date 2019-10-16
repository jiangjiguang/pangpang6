package com.pangpang6.books.offer.chapter2;

public class SortWithRange {
    public static void sortAge(int[] ages, int length) {
        int oldAge = 99;
        int[] timesAge = new int[oldAge + 1];

        for (int i = 0; i < oldAge; i++) {
            timesAge[i] = 0;
        }
        for (int i = 0; i < length; i++) {
            int age = ages[i];
            ++timesAge[age];
        }

        int index = 0;
        for (int i = 0; i < oldAge; ++i) {
            for (int j = 0; j < timesAge[i]; ++j) {
                //次数
                ages[index] = i;
                ++index;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {20, 25, 30, 21, 20};
        sortAge(data, data.length);
        System.out.println(data);
    }

}
