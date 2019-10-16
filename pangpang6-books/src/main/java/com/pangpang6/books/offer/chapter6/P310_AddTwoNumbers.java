package com.pangpang6.books.offer.chapter6;

/**
 * Description:不用加减乘除做加法
 **/
public class P310_AddTwoNumbers {
    public static int add(int a, int b) {
        //第一步：异或
        int sum = a ^ b;
        System.out.println("sum: " + sum);
        //第二步：按位与
        int yu = a & b;
        System.out.println("yu: " + yu);
        int carry = yu << 1;
        System.out.println("carry: " + carry);
        int temp;
        //第三步：也就是把进位和异或的和相加，但是又不能使用加法
        //因此，要重复前面两步，知道不产生进位
        while (carry != 0) {
            temp = sum;
            sum = sum ^ carry;
            int yu2 = carry & temp;
            carry = yu2 << 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(3, 5)); //8
//        System.out.println(add(3, -5)); //-2
//        System.out.println(add(0, 1));  //1
    }
}
