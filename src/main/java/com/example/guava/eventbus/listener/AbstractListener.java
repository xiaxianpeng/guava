package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:50
 */
@Slf4j
public abstract class AbstractListener {

    @Subscribe
    public void commonTask(String event) {
        log.info("Received event [{}] and will take a action by {}.{}", event, this.getClass().getSimpleName(), "commonTask");
    }
}
