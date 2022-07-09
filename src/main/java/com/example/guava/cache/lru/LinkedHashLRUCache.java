package com.example.guava.cache.lru;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author xianpeng.xia
 * on 2022/7/9 17:18
 * This class is not the thread-safe class
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {


    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {

        final private int limit;

        public InternalLRUCache(int initialCapacity, float loadFactor, int limit) {
            super(initialCapacity, loadFactor, true);
            this.limit = limit;
        }

        public InternalLRUCache(int limit) {
            this(16, 0.75f, limit);
        }

        @Override
        protected boolean removeEldestEntry(Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;

    private final InternalLRUCache<K, V> cache;

    public LinkedHashLRUCache(int limit) {
        Preconditions.checkArgument(limit > 0, "The limit big than zero.");
        this.limit = limit;
        this.cache = new InternalLRUCache(limit);
    }

    @Override
    public void put(K key, V val) {
        this.cache.put(key, val);
    }

    @Override
    public V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public void remove(K key) {
        this.cache.remove(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return cache.toString();
    }

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
