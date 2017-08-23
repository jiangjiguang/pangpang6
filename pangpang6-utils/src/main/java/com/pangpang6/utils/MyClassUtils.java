package com.pangpang6.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * Created by jiangjg on 2017/6/28.
 */
public class MyClassUtils {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);
    public static Object createInstance(String className){
        try {
            Class clzz = Class.forName(className);
            try {
                Constructor cc = clzz.getConstructor();
                Object obj = cc.newInstance();
                return obj;
            } catch (Exception e) {
                logger.error("",e);
            }
        } catch (ClassNotFoundException e) {
            logger.error("",e);
        }
        return null;
    }
}
