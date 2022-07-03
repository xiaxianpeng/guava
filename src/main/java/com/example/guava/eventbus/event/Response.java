package com.example.guava.eventbus.event;

import lombok.Data;

/**
 * @author xianpeng.xia
 * on 2022/7/3 13:19
 */
@Data
public class Response {

    private String orderNo;

    public Response() {
    }

    public Response(String orderNo) {
        this.orderNo = orderNo;
    }
}
