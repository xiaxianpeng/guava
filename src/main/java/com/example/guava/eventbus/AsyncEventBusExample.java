package com.example.guava.eventbus;

import com.example.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.AsyncEventBus;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xianpeng.xia
 * on 2022/7/4 00:36
 *
 * 异步event bus
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
