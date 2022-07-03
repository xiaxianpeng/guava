package com.example.guava.eventbus.internal;

/**
 * @author xianpeng.xia
 * on 2022/7/3 22:34
 * 异常处理接口
 */
public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);

}
