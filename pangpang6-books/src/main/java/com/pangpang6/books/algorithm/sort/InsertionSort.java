package com.pangpang6.books.algorithm.sort;

/**
 * Created by root on 17-5-21.
 */
//插入排序  时间复杂度 n的平方
public class InsertionSort {
    public static void main(String[] args) {
        Integer[] arr = {5,2,4,6,1,3};
        sort(arr);
        for(int i : arr){
            System.out.println(i);
        }

    }


    public static void sort(Integer[] param){
        for(int j=1; j<param.length; j++){
            int key = param[j];
            int i = j -1;
            while (i >=0 && param[i] > key){
                param[i+1] = param[i];
                i = i - 1;
            }
            param[i+1] = key;
        }
    }
}
