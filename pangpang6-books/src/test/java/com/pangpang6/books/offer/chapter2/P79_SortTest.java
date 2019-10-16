package com.pangpang6.books.offer.chapter2;

import org.junit.Test;

public class P79_SortTest {
    int[] data = {2, 3, 1, 0, 2, 5, 3};
    int[] data2 = {2, 3, 5, 4, 3, 2, 6, 6};

    @Test
    public void quickSortTest() {
        P79_Sort.quickSort(data);
        System.out.println(data);
    }

    @Test
    public void mergeSortTest() {
        P79_Sort.mergeSort(data);
        System.out.println(data);
    }

    @Test
    public void heapSortTest() {
        P79_Sort.heapSort(data);
        System.out.println(data);
    }

    @Test
    public void test(){
        int[] data = {2, 3, 1, 0, 2, 5, 3};
        ++data[0];

        System.out.println(data);
    }
}
