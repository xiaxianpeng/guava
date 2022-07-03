package com.example.guava.eventbus;

import com.example.guava.eventbus.event.Apple;
import com.example.guava.eventbus.event.Fruit;
import com.example.guava.eventbus.listener.ConcreateListener;
import com.example.guava.eventbus.listener.FruitEaterListener;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:38
 * 继承Event
 */
public class InheritEventEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());

        eventBus.post(new Fruit("fruit"));
        System.out.println("=========");
        eventBus.post(new Apple("apple"));

    }
}
