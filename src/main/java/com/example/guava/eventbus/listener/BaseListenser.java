package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:02
 */
@Slf4j
public class BaseListenser extends AbstractListener {

    @Subscribe
    public void baseTask(String event) {
        log.info("Received event [{}] and will take a action by {}.{}", event, this.getClass().getSimpleName(), "baseTask");
    }
}
