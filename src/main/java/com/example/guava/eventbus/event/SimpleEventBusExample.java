package com.example.guava.eventbus.event;

import com.example.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:38
 */
public class SimpleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
    }
}
