package com.pangpang6.books.offer.chapter3;

/**
 *
 */
public class P114_Print1ToMaxOfNDigits {
    //在字符串上模拟加法
    public static void print1ToMaxOfNDigits(int num) {
        if (num <= 0) {
            return;
        }
        StringBuilder number = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            number.append('0');
        }
        while (increment(number)) {
            printNumber(number);
        }
    }

    //字符串加1
    public static boolean increment(StringBuilder str) {
        int size = str.length() - 1;
        //最后的位  就是最小的位，因此从最后开始遍历
        for (int i = size; i >= 0; i--) {
            //最低位不是 9就加1
            if (str.charAt(i) < '9' && str.charAt(i) >= '0') {
                str.setCharAt(i, (char) (str.charAt(i) + 1));
                return true;
            } else if (str.charAt(i) == '9') {
                //最低位 已经遍历完了，需要进位了  进位后i减1
                str.setCharAt(i, '0');
            } else {
                return false;
            }
        }
        return false;
    }

    //打印字符串
    public static void printNumber(StringBuilder number) {
        boolean flag = false;
        //是从最左，即高位开始打印
        for (int i = 0; i < number.length(); i++) {
            if (flag) {
                //继续打印最大会后面的数字  不换行
                System.out.print(number.charAt(i));
            } else {
                if (number.charAt(i) != '0') {
                    flag = true;
                    //打印前面的数字  不换行
                    System.out.print(number.charAt(i));
                }
            }
        }
        //最后再换行
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits(2);
    }
}
