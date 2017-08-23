package com.pangpang6.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangjg on 2017/6/9.
 */
public class MongoMapKeyUtils {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("chloro.userprofile.downstream.http.queue.size", 1);
        System.out.println(mongoMapKeyEncode(map));

        System.out.println(mongoMapKeyDecode(mongoMapKeyEncode(map)));

        System.out.println(mongoMapKeyDecode(mongoMapKeyEncode(map)));
    }


    public static <T> Map<String, T> mongoMapKeyEncode(Map<String, T> map){
        Map<String, T> rMap = new HashMap<>();
        if(org.apache.commons.collections.MapUtils.isEmpty(map)){
            return rMap;
        }

        for(String key : map.keySet()){
            rMap.put(Encodes.encodeBase64(key), (T) map.get(key));
        }
        return rMap;
    }
    public static <T> Map<String, T> mongoMapKeyDecode(Map<String, T> map){
        Map<String, T> rMap = new HashMap<>();
        if(org.apache.commons.collections.MapUtils.isEmpty(map)){
            return rMap;
        }

        for(String key : map.keySet()){
            rMap.put(Encodes.decodeBase64String(key), map.get(key));
        }
        return rMap;
    }
}
