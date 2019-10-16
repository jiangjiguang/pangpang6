package com.pangpang6.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MyStringUtils {


    private static final Map<String, String> unionIdMap = new HashMap<String, String>() {
        {
            put("guid_str", "guid");
            put("mtdpid", "uuid");
            put("iuuid", "uuid");
        }
    };

    //返回第一个为null参数
    public static String coalesce(String... params) {
        if (params == null) return null;

        for (String param : params) {
            if (param != null) {
                return param;
            }
        }
        return null;
    }

    public static List<String> strToList(String str, String split) {
        if (StringUtils.isBlank(str) || StringUtils.isEmpty(split)) {
            return new ArrayList<>();
        }
        return Splitter.on(split).trimResults().omitEmptyStrings().splitToList(str);
    }

    public static List<Integer> strToIntegerList(String str, String split) {
        List<Integer> list = Lists.newArrayList();
        if (StringUtils.isBlank(str) || StringUtils.isEmpty(split)) {
            return list;
        }
        Iterable<String> iterable = Splitter.on(split).trimResults().omitEmptyStrings().split(str);
        if (iterable == null) {
            return list;
        }
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Integer item = Ints.tryParse(iterator.next());
            if (item == null) {
                continue;
            }
            list.add(item);
        }
        return list;
    }

    public static Set<Integer> strToIntegerSet(String str, String split) {
        Set<Integer> resultSet = Sets.newHashSet();
        if (StringUtils.isBlank(str) || StringUtils.isEmpty(split)) {
            return resultSet;
        }
        Iterable<String> iterable = Splitter.on(split).trimResults().omitEmptyStrings().split(str);
        if (iterable == null) {
            return resultSet;
        }
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Integer item = Ints.tryParse(iterator.next());
            if (item == null) {
                continue;
            }
            resultSet.add(item);
        }
        return resultSet;
    }

    public static String listToString(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        return Joiner.on(",").skipNulls().join(list);
    }

    public static String formatUnionId(String unionId) {
        if (unionId == null) {
            return null;
        }
        unionId = unionId.trim();
        String result = unionId.replaceAll("\\\\", "").replaceAll("\\\"", "");
        int index = result.indexOf(".");
        if (index > 0) {
            return result.substring(0, index);
        }
        return result;
    }

    public static String uniformUnionId(String unionId) {
        if (StringUtils.isBlank(unionId)) {
            return unionId;
        }
        String result = unionIdMap.get(unionId);
        if (StringUtils.isNotBlank(result)) {
            return result;
        }
        return unionId;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }


    public static void main(String[] args) {
        System.out.println(formatUnionId("0000000000000250986A8F7B14F98ADB884530BD77881A154230027981697268"));
    }
}
