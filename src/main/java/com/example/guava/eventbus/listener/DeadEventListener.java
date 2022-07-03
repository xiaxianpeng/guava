package com.example.guava.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:49
 */
@Slf4j
public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
