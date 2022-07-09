package com.example.guava.cache.lru;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author xianpeng.xia
 * on 2022/7/10 00:17
 */
public class SoftLRUCache<K, V> implements LRUCache<K, V> {

    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, SoftReference<V>> {

        final private int limit;

        public InternalLRUCache(int initialCapacity, float loadFactor, int limit) {
            super(initialCapacity, loadFactor, true);
            this.limit = limit;
        }

        public InternalLRUCache(int limit) {
            this(16, 0.75f, limit);
        }

        @Override
        protected boolean removeEldestEntry(Entry<K, SoftReference<V>> eldest) {

            return this.size() > limit;
        }
    }

    private final int limit;
    private final InternalLRUCache<K, V> cache;

    public SoftLRUCache(int limit) {
        this.limit = limit;
        this.cache = new InternalLRUCache<>(limit);
    }

    @Override
    public void put(K key, V val) {
        this.cache.put(key, new SoftReference<>(val));
    }

    @Override
    public V get(K key) {
        SoftReference<V> reference = this.cache.get(key);
        return reference == null ? null : reference.get();
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

    /**
     * vm: -Xms128M -Xms64M -XX:printGCDetails
     */
    public static void main(String[] args) throws InterruptedException {
        SoftLRUCache<String, byte[]> cache = new SoftLRUCache<>(100);
        for (int i = 0; i < 1000; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("The " + i + " entity is cached.");
        }
    }
}
