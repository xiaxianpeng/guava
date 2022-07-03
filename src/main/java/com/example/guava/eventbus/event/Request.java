package com.example.guava.eventbus.event;

import lombok.Data;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:19
 */
@Data
public class Request {

    private String orderNo;

    public Request(String orderNo) {
        this.orderNo = orderNo;
    }
}
