package com.example.guava.cache.lru;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author xianpeng.xia
 * on 2022/7/9 17:38
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {

    private final int limit;
    private final LinkedList<K> keys = new LinkedList<>();
    private final Map<K, V> cache = new HashMap<>();

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V val) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(val);
        if (this.keys.size() >= limit) {
            K oldestKey = keys.removeFirst();
            this.cache.remove(oldestKey);
        }
        this.keys.addLast(key);
        this.cache.put(key, val);
    }

    @Override
    public V get(K key) {
        boolean exist = this.keys.contains(key);
        if (!exist) {
            return null;
        }
        this.keys.addLast(key);
        return this.cache.get(key);
    }

    @Override
    public void remove(K key) {
        boolean exist = this.keys.remove(key);
        if (exist) {
            this.cache.remove(key);
        }
    }

    @Override
    public int size() {
        return this.keys.size();
    }

    @Override
    public void clear() {
        this.keys.clear();
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (K key : keys) {
            sb.append(key).append("=").append(this.cache.get(key)).append(";");
        }
        return sb.toString();
    }
}
