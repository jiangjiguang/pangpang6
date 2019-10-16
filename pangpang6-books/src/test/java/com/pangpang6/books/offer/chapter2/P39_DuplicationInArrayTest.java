package com.pangpang6.books.offer.chapter2;

import org.junit.Test;

public class P39_DuplicationInArrayTest {
    int[] data = {2, 3, 1, 0, 2, 5, 3};
    int[] data2 = {2, 3, 5, 4, 3, 2, 6, 6};

    @Test
    public void getDuplication5Test() {
        int r = P39_DuplicationInArray.getDuplication5(data2);
        System.out.println(r);
    }

    @Test
    public void quickSortTest() {
        P39_DuplicationInArray.quickSort(data, 0, data.length - 1);
        System.out.println(data);
    }

    @Test
    public void getDuplication2Test() {
        int result = P39_DuplicationInArray.getDuplication2(data);
        System.out.println(result);
    }


    @Test
    public void getDuplicationTest() {
        int result = P39_DuplicationInArray.getDuplication(data);
        System.out.println(result);
    }


}
