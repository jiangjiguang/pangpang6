package com.pangpang6.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jiangjg on 2017/4/11.
 */
public class Base64Utils {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = Base64.encodeBase64("chloro.userprofile.downstream.http.queue.size".getBytes("utf-8"));

        System.out.println(new String(bytes));
        byte[] aa = Base64.decodeBase64(bytes);
        System.out.println(new String(aa));

        String ss = URLEncoder.encode("chloro.userprofile.downstream.http.queue.size", "UTF-8");


        System.out.println(ss);
    }
}
