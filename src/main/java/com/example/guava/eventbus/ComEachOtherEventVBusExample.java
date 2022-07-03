package com.example.guava.eventbus;

import com.example.guava.eventbus.service.QueryService;
import com.example.guava.eventbus.service.RequestQueryHandler;
import com.google.common.eventbus.EventBus;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:35
 */
public class ComEachOtherEventVBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        eventBus.register(new RequestQueryHandler(eventBus));
        queryService.query("no1");
    }
}
