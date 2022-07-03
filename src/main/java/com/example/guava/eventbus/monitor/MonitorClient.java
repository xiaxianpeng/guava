package com.example.guava.eventbus.monitor;

import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 15:25
 * 分布式的海量日志采集、聚合和传输的软件Apache Flume
 * https://flume.apache.org/
 */
public class MonitorClient {

    public static void main(String[] args) throws Exception {

        String targetPath = "/Users/roger/Project/Own/guava/doc";
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, targetPath);
        monitor.startMonitor();
    }
}
