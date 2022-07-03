package com.example.guava.eventbus;

import com.example.guava.eventbus.listener.DeadEventListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:12
 */
public class DeadEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("DeadEventBus") {
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        eventBus.register(new DeadEventListener());
        eventBus.post("Hello");
    }
}
