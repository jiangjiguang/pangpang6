package com.pangpang6.books.guava.base;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/11.
 */
public class CharMatcherTest {
    @Test
    public void anyTest(){
        System.out.println(CharMatcher.any().retainFrom("Amahesh123姜纪光"));
    }

    @Test
    public void anyOfTest(){
        System.out.println(CharMatcher.anyOf("a").retainFrom("Amahesh123姜纪光"));
    }

    @Test
    public void asciiTest(){
        String result = CharMatcher.ascii().retainFrom("Amahesh123姜纪光@!!!");
        System.out.println(result);
    }

    @Test
    public void breakingWhitespaceTest(){
        String result = CharMatcher.breakingWhitespace().retainFrom("this is a test!");
        System.out.println(result.length());
    }

    @Test
    public void collapseFromTest(){
        String result = CharMatcher.breakingWhitespace().collapseFrom("this is a test!", '-');
        System.out.println(result);
    }

    @Test
    public void countInTest(){
        int result = CharMatcher.breakingWhitespace().countIn("this is a test!");
        System.out.println(result);
    }

    @Test
    public void digitTest(){
        int result = CharMatcher.digit().countIn("this is 1 test!");
        System.out.println(result);
    }

    @Test
    public void forPredicateTest(){
        int result = CharMatcher.forPredicate(x -> Character.isDigit(x)).countIn("this is 1 test!");
        System.out.println(result);
    }

    @Test
    public void indexInTest(){
        int result = CharMatcher.forPredicate(x -> Character.isDigit(x)).indexIn("this is 1 test!");
        System.out.println(result);
    }

    @Test
    public void isTest(){
        int result = CharMatcher.is('a').countIn("this is a test!");
        System.out.println(result);
    }

    @Test
    public void matchesTest(){
        boolean result = CharMatcher.is('a').matches('b');
        System.out.println(result);
    }

    @Test
    public void orTest(){
        int result = CharMatcher.is('a').or(CharMatcher.javaLowerCase()).countIn("This a");
        System.out.println(result);
    }

}
