package com.pangpang6.books.algorithm.sort;

/**
 * Created by root on 17-5-21.
 */
public class maopao {
    public static void main(String[] args){
        int []nums = {5,4,3,2,1};
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length-i-1; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
            for(int x = 0;x < nums.length;x++){
                System.out.print(nums[x]+",");
            }
            System.out.print("\n");
        }
    }
}
