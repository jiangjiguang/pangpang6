package com.pangpang6.books.offer.chapter3;

/**
 * 正则表达式匹配
 * 完成.(任何一个字符)和*(前面字符的任意次数)
 */
public class P124_RegularExpressionsMatching {
    public static boolean match(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return matchCore(new StringBuilder(str), 0, new StringBuilder(pattern), 0);
    }

    public static boolean matchCore(StringBuilder str, Integer strIndex, StringBuilder pattern, Integer patternIndex) {
        //如果匹配串和模式串都匹配结束, 则认为成功匹配
        if (patternIndex >= pattern.length() && strIndex >= str.length()) {
            return true;
        }
        //如果匹配串没结束，模式串结束了，则认为不匹配
        if (patternIndex >= pattern.length() && strIndex < str.length()) {
            return false;
        }
        //模式串没结束，匹配串结束了
        if (patternIndex < pattern.length() && strIndex == str.length()) {
            //模式串至少还有两个字符，若第二个字符是*，那么匹配串跳过两个
            if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            } else {
                return false;
            }
        }
        //如果模式串的第二个字符不是*或者已经只剩一个字符了
        if (patternIndex == pattern.length() - 1 || pattern.charAt(patternIndex + 1) != '*') {
            if (pattern.charAt(patternIndex) == '.' || pattern.charAt(patternIndex) == str.charAt(strIndex)) {
                return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
            } else {
                return false;
            }
        }
        //如果模式串的第二个字符是*
        else {
            if (pattern.charAt(patternIndex) == '.' || pattern.charAt(patternIndex) == str.charAt(strIndex)) {
                return matchCore(str, strIndex + 1, pattern, patternIndex)
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)
                        || matchCore(str, strIndex, pattern, patternIndex + 2);
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(match("aaa", "a.a"));//true
//        System.out.println(match("aaa", "ab*ac*a"));//true
//        System.out.println(match("aaa", "aa.a"));//false
//        System.out.println(match("aaa", "ab*a"));//false
        System.out.println(match("abc", "abc*"));//true

    }
}
