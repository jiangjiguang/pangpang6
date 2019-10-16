package com.pangpang6.hadoop.hadoop2.fof;

/**
 * Created by jiangjiguang on 2018/3/27.
 */
public class FofUtils {

    public static String format(String friend1, String friend2) {
        int c = friend1.compareTo(friend2);

        if (c < 0) {
            return String.format("%s-%s", friend2, friend1);
        }

        return String.format("%s-%s", friend1, friend2);
    }
}
