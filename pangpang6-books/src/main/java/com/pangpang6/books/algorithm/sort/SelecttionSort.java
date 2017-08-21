package com.pangpang6.books.algorithm.sort;

/**
 * Created by root on 17-5-21.
 */
//选择排序  时间复杂度n的平方
public class SelecttionSort {
    public static void main(String[] args) {
        Integer[] arr = {5,2,4,6,1,3};
        sort(arr);
        for(int i : arr){
            System.out.println(i);
        }

    }

    public static void sort(Integer[] param){
        for(int j=0; j<param.length - 1; j++){
            int key = param[j];
            int index = j;

            int i = j + 1;
            while (i < param.length){
                if(key > param[i]){
                    key = param[i];
                    index = i;
                }
                i = i + 1;
            }
            param[index] = param[j];
            param[j] = key;

            //System.out.println(key);
        }
    }
}
