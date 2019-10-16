package com.pangpang6.books.guava.eventbus;

import com.google.common.eventbus.*;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/18.
 */
//观察者模式的一种实现
public class EventBusTest {


    @Test
    public void registerTest() {
        EventBus eventBus = new EventBus(new SubscriberExceptionHandler() {
            @Override
            public void handleException(Throwable exception, SubscriberExceptionContext context) {
                System.out.println(context.getEvent());
            }
        });
        EventListener listener = new EventListener();
        eventBus.register(listener);
        DeadEventListener eventListener = new DeadEventListener();
        eventBus.register(eventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));
        eventBus.post("dsdddd");
        System.out.println("LastMessage:" + listener.getLastMessage());
        System.out.println(eventBus.identifier());
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

    public class EventListener {
        public int lastMessage = 0;

        @Subscribe
        public void receive(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("receive:" + lastMessage);
        }

        @Subscribe
        public void receive2(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("receive2:" + lastMessage);
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }

    public class DeadEventListener {
        boolean notDelivered = false;

        @Subscribe
        public void listen(DeadEvent event) {
            System.out.println("dead:  " + event.getEvent());
            notDelivered = true;
        }

        public boolean isNotDelivered() {
            return notDelivered;
        }
    }
}
