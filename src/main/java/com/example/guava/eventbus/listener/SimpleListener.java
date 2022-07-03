package com.example.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 09:35
 *
 * 单订阅者监听者
 */
@Slf4j
public class SimpleListener {

    @Subscribe
    public void doAction(final String event) {
        log.info("Received event [{}] and will take a action", event);
    }

}
