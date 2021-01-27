package com.pangpang6.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

import java.util.Map;

public class ConfigTest {

    @Test
    public void test01() {
        Map<String, Object> map = Maps.newHashMap();

        map.put("k1", "v1");
        map.put("k2", 2);

        Map<String, Object> subMap = Maps.newHashMap();
        subMap.put("k3", "v3");
        map.put("subMap", subMap);

        String content = JSON.toJSONString(map);

        System.out.println(content);

        Config akkaConfig = ConfigFactory.parseString(content);

        System.out.println(akkaConfig.getString("k1"));
        System.out.println(akkaConfig.getString("k2"));
        System.out.println((akkaConfig.getString("subMap.k3")));
        System.out.println(akkaConfig);
    }
}
