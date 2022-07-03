package com.example.guava.eventbus.monitor;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 15:22
 */
@Slf4j
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        log.info("path:{} --- kind:{}", event.getPath(), event.getKind());
    }
}
