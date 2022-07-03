package com.example.guava.eventbus.listener;

import com.example.guava.eventbus.event.Apple;
import com.example.guava.eventbus.event.Fruit;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:14
 */
@Slf4j
public class FruitEaterListener {

    @Subscribe
    public void eat(final Fruit event) {
        log.info("Fruit eat [{}].", event);
    }


    @Subscribe
    public void eat(final Apple event) {
        log.info("Apple eat [{}].", event);
    }

}
