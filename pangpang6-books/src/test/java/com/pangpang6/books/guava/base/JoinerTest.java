package com.pangpang6.books.guava.base;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class JoinerTest {

    @Test
    public void joinTest(){
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("1");
        list.add("3");

        String r1 = Joiner.on(",").join(list);
        System.out.println(r1);

        Map<String, String> map = new HashMap<>();
        map.put("k3", "v3");
        map.put("k1", "v1");
        map.put("k2", "v2");

        String r2 = Joiner.on(",").withKeyValueSeparator(";").join(map);
        System.out.println(r2);
    }

    @Test
    public void appendToTest(){
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("1");
        list.add("3");
        StringBuilder sb = new StringBuilder();
        sb.append("wwww");
        Joiner.on(",").appendTo(sb, list);
        System.out.println(sb);

    }
}
