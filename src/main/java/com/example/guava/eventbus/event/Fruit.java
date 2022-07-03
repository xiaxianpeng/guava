package com.example.guava.eventbus.event;

import lombok.Data;

/**
 * @author xianpeng.xia
 * on 2022/7/3 10:13
 */
@Data
public class Fruit {

    private String name;

    public Fruit(String name) {
        this.name = name;
    }

}
