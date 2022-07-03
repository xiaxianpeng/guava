package com.example.guava.eventbus.internal;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xianpeng.xia
 * on 2022/7/4 00:34
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(String busName, EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName, exceptionHandler, executor);
    }


    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        super(busName, null, executor);
    }


    public AsyncEventBus(ThreadPoolExecutor executor) {
        super("default-async", null, executor);
    }


    public AsyncEventBus(EventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super("default-async", exceptionHandler, executor);
    }
}
