package com.example.guava.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @author xianpeng.xia
 * on 2022/7/3 22:35
 *
 * 上下文
 */
public interface EventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent() ;
}
