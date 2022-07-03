package com.example.guava.eventbus;

import com.example.guava.eventbus.listener.DeadEventListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:12
 */
public class DeadEventBusExample {

    public static void main(String[] args) {
        final DeadEventListener deadEventListener = new DeadEventListener();
        final EventBus eventBus = new EventBus("DeadEventBus") {
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        eventBus.register(deadEventListener);
        eventBus.post("Hello");

        eventBus.unregister(deadEventListener);
        eventBus.post("Hello");
        eventBus.post("Hello");
        eventBus.post("Hello");
        eventBus.post("Hello");

    }
}
