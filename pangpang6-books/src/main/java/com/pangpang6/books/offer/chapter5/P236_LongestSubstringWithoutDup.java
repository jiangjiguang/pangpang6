package com.pangpang6.books.offer.chapter5;

/**
 * Description:最长不含重复字符的子字符串
 **/
public class P236_LongestSubstringWithoutDup {

    private static int findLongestSubstringLength(String string) {
        if (string == null || string.equals("")) {
            return 0;
        }
        int maxLength = 0;
        int curLength = 0;
        int[] positions = new int[26];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = -1; //初始化为-1，负数表示没出现过
        }
        for (int i = 0; i < string.length(); i++) {
            int curChar = string.charAt(i) - 'a';
            //目前字符先前出现的位置
            int prePosition = positions[curChar];
            //当前字符与它上次出现位置之间的距离
            int distance = i - prePosition;
            //当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
            if (prePosition < 0 || distance > curLength) {
                curLength++;
            } else {
                //更新最长非重复子字符串的长度
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                curLength = distance;
            }
            positions[curChar] = i; //更新字符出现的位置
        }

        if (curLength > maxLength) {
            maxLength = curLength;
        }
        return maxLength;
    }


    //动态规划
    //dp[i]表示以下标为i的字符结尾不包含重复字符的最长子字符串长度
    public static int longestSubstringWithoutDup(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        //dp数组可以省略，因为只需记录前一位置的dp值即可
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int maxdp = 1;
        for (int dpIndex = 1; dpIndex < dp.length; dpIndex++) {
            //i最终会停在重复字符或者-1的位置,要注意i的结束条件
            //i 表示前面的字符
            int i = dpIndex - 1;

            //t 表示
            int indexMax = dpIndex - dp[dpIndex - 1];  //当前元素下标 - 前一个元素的最大距离 = 当前元素的最大可能距离
            for (; i >= indexMax; i--) {
                //如果当前元素 等于 前面的任意元素 则停止计数
                if (str.charAt(dpIndex) == str.charAt(i)) {
                    break;
                }
            }
            //i就是和当前元素重复的位置
            dp[dpIndex] = dpIndex - i;
            if (dp[dpIndex] > maxdp) {
                maxdp = dp[dpIndex];
            }
        }
        return maxdp;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithoutDup("arabcacfr"));
        //System.out.println(longestSubstringWithoutDup("abcdefaaa"));


        //System.out.println(findLongestSubstringLength("abcdefaaa"));

    }
}
