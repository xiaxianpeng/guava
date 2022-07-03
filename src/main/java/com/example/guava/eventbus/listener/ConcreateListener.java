package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:03
 */
@Slf4j
public class ConcreateListener extends BaseListenser {

    @Subscribe
    public void concreateTask(String event) {
        log.info("Received event [{}] and will take a action by {}.{}", event, this.getClass().getSimpleName(), "concreateTask");
    }
}
