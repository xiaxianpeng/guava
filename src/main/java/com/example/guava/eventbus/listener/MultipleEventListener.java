package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:42
 * 多订阅者监听者
 */
@Slf4j
public class MultipleEventListener {

    @Subscribe
    public void task1(String event) {
        log.info("Received event [{}] and will take a action by ===task1===", event);
    }

    @Subscribe
    public void task2(String event) {
        log.info("Received event [{}] and will take a action by ===task2===", event);
    }

    @Subscribe
    public void intTask(Integer event) {
        log.info("Received event [{}] and will take a action by ===intTask===", event);
    }
}
