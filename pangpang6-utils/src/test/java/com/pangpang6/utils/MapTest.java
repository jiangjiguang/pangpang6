package com.pangpang6.utils;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapTest {

    @Test
    public void putTest() {
        Map<String, String> map = Maps.newHashMap();

        System.out.println(map.put("key1", "value1"));
        System.out.println(map.put("key1", "value2"));
        System.out.println(map.put("key1", "value2"));
    }

    @Test
    public void test01() {
        String[] s = new String[]{"k1", "k2"};
        System.out.println(s.length);
        System.out.println(Arrays.toString(s));
    }

    @Test
    public void test02() {
        List<Integer> templatePos = new ArrayList<>();
        templatePos.add(1);
        templatePos.add(2);

        System.out.println(templatePos.toString());
    }
}
