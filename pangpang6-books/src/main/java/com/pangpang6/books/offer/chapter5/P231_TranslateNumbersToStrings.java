package com.pangpang6.books.offer.chapter5;

/**
 * Description:把数字翻译成字符串(0-25翻译成a-z)
 **/
public class P231_TranslateNumbersToStrings {
    public static int getTranslationCount(int number) {
        if (number < 0)
            return 0;
        if (number == 1) {
            return 1;
        }
        return getTranslationCount(Integer.toString(number));
    }

    //动态规划，从右到左计算。
    //f(r-2) = f(r-1)+g(r-2,r-1)*f(r);
    //如果r-2，r-1能够翻译成字符，则g(r-2,r-1)=1，否则为0
    public static int getTranslationCount(String number) {
        int f1 = 0;
        int f2 = 1;
        int g;
        int temp;
        for (int i = number.length() - 2; i >= 0; i--) {
            String t = number.charAt(i) + "" + number.charAt(i + 1);
            if (Integer.parseInt(t) < 26) {
                g = 1;
            } else {
                g = 0;
            }
            temp = f2;
            f2 = f2 + g * f1;
            f1 = temp;
        }
        return f2;
    }

//数字重复使用
//    public static int count2(int number) {
//        if (number < 0) {
//            return 0;
//        }
//
//        String input = number + "";
//        if (input.length() <= 1) {
//            return 1;
//        }
//
//        int count = 0;
//        int end = input.length() - 1;
//        for (int i = end; i >= 0; i--) {
//            int iPlus = i + 1;
//            if (iPlus >= end) {
//                iPlus = end;
//            }
//            if (i == iPlus) {
//                count += 1;
//                continue;
//            }
//            count += 1;
//            int t = Integer.parseInt(input.substring(i, iPlus + 1));
//            if (t < 26) {
//                count += 1;
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        System.out.println(getTranslationCount(-10));  //0
        System.out.println(getTranslationCount(1234));  //3
        System.out.println(getTranslationCount(12258)); //5

        //System.out.println(count2(12258));
    }
}
