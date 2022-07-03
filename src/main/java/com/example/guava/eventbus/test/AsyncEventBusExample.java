package com.example.guava.eventbus.test;

import com.example.guava.eventbus.internal.AsyncEventBus;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xianpeng.xia
 * on 2022/7/4 00:36
 */
public class AsyncEventBusExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        AsyncEventBus eventBus = new AsyncEventBus(executor);
        eventBus.register(new SimpleListener());

        eventBus.post("async");
        eventBus.post("event bus");

    }
}
