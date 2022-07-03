package com.example.guava.eventbus.test;

import com.example.guava.eventbus.internal.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/4 00:12
 */
@Slf4j
public class SimpleListener {

    @Subscribe
    public void print(String x) {
        log.info("SimpleListener print:{}", x);
    }

    @Subscribe(topic = "print-int")
    public void printInt(Integer i) {
        log.info("SimpleListener printInt:{}", i);
    }

    @Subscribe(topic = "ex-print-int")
    public void exception(Integer i) {
        throw new RuntimeException();
    }

    @Subscribe
    public void longTimePrint(String x) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("SimpleListener longTimePrint:{}", x);
    }
}
