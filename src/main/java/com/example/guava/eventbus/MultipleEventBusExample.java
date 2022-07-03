package com.example.guava.eventbus;

import com.example.guava.eventbus.listener.MultipleEventListener;
import com.example.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:38
 * 单订阅者发布者
 */
public class MultipleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListener());
        System.out.println("post the multiple event.");
        eventBus.post("I am string event");

        System.out.println("post the int event.");
        eventBus.post(1);
    }
}
