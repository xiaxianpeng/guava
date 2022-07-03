package com.example.guava.eventbus.internal;

/**
 * @author xianpeng.xia
 * on 2022/7/3 15:55
 * bus接口类
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event, String topic);

    void close();

    String getBusName();
}
