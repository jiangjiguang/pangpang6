package com.pangpang6.utils;

import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyu on 2016/6/21.
 */
public class MapUtils {


    /**
     * 把AvroObject转换成Map
     * @param obj
     * @return
     */
    public static Map convertAvroObjectToMap(Object obj){
        return getMapFromObject(obj,10,0);
    }


    private static Map getMapFromObject(Object obj,int maxDeep, int deep) {
        deep++;

        if (obj == null)
            return null;

        if (deep >= maxDeep)
            return null;

        Map map = new HashMap();
        try {
            Method[] methods = obj.getClass().getMethods();

            for (Method m : methods) {
                String methodName = m.getName();
                if (methodName.startsWith("get") && methodName.length() > 3 && !methodName.equalsIgnoreCase("getClass") && !methodName.equalsIgnoreCase("getClassSchema")
                        && !methodName.equalsIgnoreCase("getSchema") && !methodName.equalsIgnoreCase("getType") && !methodName.equalsIgnoreCase("getEventType")) {
                    try {
                        if (m.getReturnType().getClass().isEnum())
                            continue;

                        // Integer
                        // Long
                        // Double
                        // CharSequence
                        // Map
                        // java.lang.Boolean

                        if (m.getReturnType().isPrimitive() || m.getReturnType() == Integer.class || m.getReturnType() == Long.class || m.getReturnType() == Double.class
                                || m.getReturnType() == Boolean.class) {
                            Object v = m.invoke(obj, null);
                            map.put(methodName.substring(3), v == null ? "" : v.toString());
                        } else if (m.getReturnType() == CharSequence.class) {
                            CharSequence cs = (CharSequence) m.invoke(obj, null);
                            map.put(methodName.substring(3), cs == null ? "" : cs.toString());
                        } else if (m.getReturnType() == Map.class) {
                            Map tmpMap = (Map) m.invoke(obj, null);
                            /*
                            Map newMap = new HashMap();
                            for (Object tmpKey : tmpMap.keySet()) {
                                newMap.put(tmpKey.toString(), tmpMap.get(tmpKey) == null ? "" : tmpMap.get(tmpKey).toString());
                            }
                            */
                            map.put(methodName.substring(3), tmpMap);
                        } else if(m.getReturnType() == List.class){
                            List tmplst = (List) m.invoke(obj, null);
                            map.put(methodName.substring(3), tmplst);
                        } else {
                            Map newMap = getMapFromObject(m.invoke(obj, null),maxDeep, deep);
                            map.put(methodName.substring(3), newMap);
                        }
                    } catch (Exception e) {
                        //logger.error(ExceptionUtils.getFullStackTrace(e));
                    }
                }
            }
        } catch (Exception e) {
            //logger.warn(ExceptionUtils.getFullStackTrace(e));
        }
        return map;
    }

    /**
     * 获取map的string值。key区分大小写
     * @param map
     * @param keyExpression  支持key1.key3+"-"+key2格式
     * @return
     */
    public static String getStringValueWithKeyExpression(Map map, String keyExpression) {
        return getStringValueWithKeyExpression(map, keyExpression, "");
    }

    /**
     * 获取map的string值。key区分大小写
     *
     * @param map
     * @param keyExpression key1.key2  其中key1获取的value为map类型
     * @param filter 过滤字符，执行 replace(filter , "")
     * @return
     */
    public static String getStringValueWithKeyExpression(Map map, String keyExpression,String filter) {
        if (map == null || StringUtils.isBlank(keyExpression))
            return "";
        try {
            StringBuilder sb = new StringBuilder();
            List<String> keyValueExpressions = Splitter.on("+").omitEmptyStrings().splitToList(keyExpression);

            for (String tmp : keyValueExpressions) {
                //处理key为  key1 + "-" + key2 格式
                if (tmp.startsWith("\"") && tmp.endsWith("\"")) {
                    sb.append(tmp.substring(1, tmp.length() - 1));
                } else {
                    if (map == null || StringUtils.isBlank(keyExpression))
                        return "";
                    List<String> keys = Splitter.on(".").omitEmptyStrings().splitToList(tmp);
                    sb.append(getStringValueWithKeys(map, keys, filter));
                }
            }
            return sb.toString();
        } catch (Exception e) {

        }
        return "";
    }


    /**
     *
     * @param map
     * @param keys
     * @return
     */
    private static String getStringValueWithKeys(Map map, List<String> keys,String filter) {
        if (keys == null)
            return "";
        for (String key : keys) {
            if (map.get(key) == null || StringUtils.isBlank(map.get(key).toString()))
                return "";
            //key=StringUtils.uncapitalize(key);
            if (Map.class.isAssignableFrom(map.get(key).getClass())) {
                map = (Map) map.get(key);
            } else {
                if(StringUtils.isNotBlank(filter))
                    return map.get(key).toString().trim().replace(filter, "");
                else{
                    return map.get(key).toString().trim();
                }
            }
        }
        return "";
    }

    /**
     * 将值放入Map中
     * @param rnt
     * @param objValue 值,if objValue is null, not put it
     * @param keyValueExpression key表达式  key1.key2  放入 map.put(key1,new map.put(key2,value));
     * @param valueDataType  INTEGER 4;FLOAT 6;TIMESTAMP 93,Other String
     */
    public static void putValueToMap(final Map rnt, Object objValue, String keyValueExpression, int valueDataType) {
        // TODO Auto-generated method stub
        if (objValue == null)
            return;
        Object putValue;
        if (valueDataType == Types.INTEGER) {
            putValue = org.apache.commons.lang.math.NumberUtils.toInt(objValue.toString(), 0);
        } else if (valueDataType == Types.FLOAT) {
            putValue = org.apache.commons.lang.math.NumberUtils.toFloat(objValue.toString(), 0);
        } else if(valueDataType == Types.TIMESTAMP){
            putValue = objValue;
        } else {
            // 作为 string处理
            putValue = objValue.toString();
        }

        Map map = rnt;

        String[] keys = keyValueExpression.split("\\.");
        for (int index = 0; index < keys.length; index++) {
            if (index == keys.length - 1)
                map.put(keys[index], putValue);
            else {
                if (map.get(keys[index]) == null) {
                    map.put(keys[index], new HashMap<>());
                }
                map = (Map) map.get(keys[index]);
            }
        }
    }

    /**
     * 清除 value 为null 或者 empty 的 keyvalue
     * @param map
     */
    public static void clearEmptyValue(Map map) {
        // TODO Auto-generated method stub
        if (map == null)
            return;

            List emptyKeys = new ArrayList<>();
            for (Object k : map.keySet()) {
                if ((map.get(k) == null)) {
                    emptyKeys.add(k);
                } else if (map.get(k) instanceof String && StringUtils.isBlank((String) map.get(k))) {
                    emptyKeys.add(k);
                } else if (map.get(k) instanceof Map) {
                    Map tMap = (Map)map.get(k);
                    clearEmptyValue(tMap);
                }
            }
            for (Object k : emptyKeys) {
                map.remove(k);
            }
    }


    /**
     * 获取map中的stringvalue
     * @param map
     * @param key
     * @return
     */
    public static String getStringValue(Map map, String key){
        if(map == null){
            return "";
        }
        Object object = map.get(key);
        if(object == null)
            return "";
        if(object instanceof String){
            return (String)object;
        }else{
            return object.toString();
        }
    }



    /**
     * 获取map中的stringvalue
     * @param map
     * @param key
     * @return
     */
    public static String getStringValueIgnoreKeyCase(Map map, String key) {

        if (map != null) {
            for (Object obj : map.keySet()) {
                if (obj.toString().equalsIgnoreCase(key)) {
                    return getStringValue(map,obj.toString());
                }
            }
        }

        return "";
    }
}
