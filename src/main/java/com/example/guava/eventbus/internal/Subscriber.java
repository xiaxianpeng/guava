package com.example.guava.eventbus.internal;

import java.lang.reflect.Method;
import lombok.Data;

/**
 * @author xianpeng.xia
 * on 2022/7/3 23:12
 * 订阅者
 */
@Data
public class Subscriber {

    private final Object subscribeObject;
    private final Method subscribeMethod;
    private boolean disable = false;

    public Subscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }
}
