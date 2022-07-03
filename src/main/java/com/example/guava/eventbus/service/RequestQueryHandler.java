package com.example.guava.eventbus.service;

import com.example.guava.eventbus.event.Request;
import com.example.guava.eventbus.event.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:20
 */
@Slf4j
public class RequestQueryHandler {

    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void doQuery(Request request) {
        log.info("Start query the orderNo [{}]", request.getOrderNo());
        Response response = new Response(request.getOrderNo());
        this.eventBus.post(response);
    }
}
