package com.pangpang6.books.guava.cache;

import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/16.
 */
public class RemovalNotificationTest {
    @Test
    public void test(){
        RemovalNotification<Integer, String> notification = RemovalNotification.create(1, "1111", RemovalCause.REPLACED);
        System.out.println(notification.wasEvicted());
    }
}
