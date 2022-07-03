package com.example.guava.eventbus.test;

import com.example.guava.eventbus.internal.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/4 00:13
 */
public class EventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus(((cause, context) -> {
            cause.printStackTrace();
            System.out.println("--------------------");
            System.out.println(context.getSource());
            System.out.println(context.getSubscribe());
            System.out.println(context.getEvent());
            System.out.println(context.getSubscriber());
        }));
        eventBus.register(new SimpleListener());

        eventBus.post("test");
        eventBus.post(1, "print-int");
        eventBus.post(1, "ex-print-int");
    }
}
