package com.example.guava.cache.lru;

/**
 * @author xianpeng.xia
 * on 2022/7/9 17:17
 */
public interface LRUCache<K, V> {

    void put(K key, V val);

    V get(K key);

    void remove(K key);

    int size();

    void clear();

    int limit();
}
