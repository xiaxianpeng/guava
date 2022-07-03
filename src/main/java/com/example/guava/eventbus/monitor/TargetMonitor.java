package com.example.guava.eventbus.monitor;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:48
 */
public interface TargetMonitor {

    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;
}
