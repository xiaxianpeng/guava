package com.example.guava.eventbus.event;

import com.example.guava.eventbus.listener.ConcreateListener;
import com.example.guava.eventbus.listener.MultipleEventListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:38
 * 继承订阅者
 */
public class InheritListenersEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreateListener());

        System.out.println("post the concreate event.");
        eventBus.post("I am string event");

        System.out.println("post the int event.");
        eventBus.post(1);
    }
}
