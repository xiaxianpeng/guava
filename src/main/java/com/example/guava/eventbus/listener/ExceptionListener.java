package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:36
 */
@Slf4j
public class ExceptionListener {

    @Subscribe
    public void m1(String event) {
        log.info("event:{} was handled by m1", event);
    }


    @Subscribe
    public void m2(String event) {
        log.info("event:{} was handled by m2", event);
    }


    @Subscribe
    public void m3(String event) {
        log.info("event:{} was handled by m3", event);
        throw new RuntimeException("ex");
    }
}
