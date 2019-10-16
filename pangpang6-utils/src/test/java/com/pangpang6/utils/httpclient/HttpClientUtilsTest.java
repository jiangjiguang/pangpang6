package com.pangpang6.utils.httpclient;

import org.apache.http.NameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjiguang on 2017/11/29.
 */
public class HttpClientUtilsTest {
    @Test
    public void executeFormTest() {
        String url = "http://whale.51ping.com/grey/match";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new NameValuePair() {
            @Override
            public String getName() {
                return "request";
            }

            @Override
            public String getValue() {
                return "qqq";
            }
        });
        HttpClientResult result = HttpClientUtils.executeForm(url, params);
        System.out.println(result.getErrorCode());
        System.out.println(result.getContent());
    }

    @Test
    public void executePostTest() {
        String url = "http://alloc.service.sankuai.com:8080/mail";
        String content = "sendMode=elephantPublicNumber&receiver=jiangjiguang&content=test";
        HttpClientUtils.executePost(url, content);
    }
}
