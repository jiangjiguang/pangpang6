package com.pangpang6.books.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jiangjiguang on 2017/12/18.
 */
public class AsyncEventBusTest {
    @Test
    public void registerTest() {
        Executor executor = Executors.newSingleThreadExecutor();

        EventBus eventBus = new AsyncEventBus(executor);
        eventBus.post(new TestEvent(100));

    }

    public class TestEvent {
        private final int message;

        public TestEvent(int message) {
            this.message = message;
            System.out.println("event message:" + message);
        }

        public int getMessage() {
            return message;
        }
    }
}
