package com.example.guava.cache.lru;

/**
 * @author xianpeng.xia
 * on 2022/7/9 17:32
 */
public class LinkedHashLRUCahceExample {

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedHashLRUCache<>(3);

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println("cache = " + cache);
        cache.put("4", "4");
        System.out.println("cache = " + cache);
        System.out.println("key = 2 , val = " + cache.get("2"));
        System.out.println("cache = " + cache);
    }
}
