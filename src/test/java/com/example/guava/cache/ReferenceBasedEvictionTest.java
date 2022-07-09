package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 00:39
 * 基于引用的回收（Reference-based Eviction），通过使用弱引用的键或值、或软引用的值，把缓存设置为允许垃圾回收器回收：
 * CacheBuilder.weakKeys()：使用弱引用存储键。当键没有其它（强或软）引用时，缓存项可以被GC回收
 * CacheBuilder.weakValues()：使用弱引用存储值。当值没有其它（强或软）引用时，缓存项可以被GC回收
 * CacheBuilder.softValues()：使用软引用存储值。软引用只有在响应内存需要时，才按照全局最近最少使用的顺序回收。影响性能，不推荐使用。
 */
public class ReferenceBasedEvictionTest {

    /**
     * Strong/soft/weak/Phantom reference
     */
    @Test
    public void testWeakKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .weakKeys()
            .weakValues()
            .build(createCacheLoader());

        Assert.assertNotNull(cache.getUnchecked("a"));
        Assert.assertNotNull(cache.getUnchecked("b"));

        System.gc();
        TimeUnit.MILLISECONDS.sleep(100);
        Assert.assertNull(cache.getIfPresent("a"));
    }


    /**
     * -ea -Xmx64M -Xms32M -XX:+PrintGCDetails
     */
    @Test
    public void testSoftKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .softValues()
            .build(createCacheLoader());

        int i = 0;
        for (; ; ) {
            cache.put("cache" + i, new Employee("cache" + 1, "cache" + 1, "cache" + 1));
            System.out.println("The Employee [" + (i++) + "] is stote into cache.");
            TimeUnit.MILLISECONDS.sleep(600);
        }
    }


    private Employee findEmployeeByName(final String name) {
        System.out.println("findEmployeeByName: " + name);
        return new Employee(name, name, name);
    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String name) throws Exception {
                return findEmployeeByName(name);
            }
        };
    }
}
