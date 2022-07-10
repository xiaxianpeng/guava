package com.example.guava.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 10:47
 * * Guava Cache之NullValue
 */
public class NullValueTest {

    @Test
    public void testLoadNullValue() {
        CacheLoader<String, Employee> cacheLoader = CacheLoader
            .from(k -> k.equals("null") ? null : new Employee(k, k, k));

        LoadingCache<String, Employee> loadingCache = CacheBuilder.newBuilder().build(cacheLoader);

        Employee alex = loadingCache.getUnchecked("Alex");
        Assert.assertEquals(alex.getName(), "Alex");

        // key为null会抛出异常
        Assert.assertThrows(CacheLoader.InvalidCacheLoadException.class,
            () -> loadingCache.getUnchecked("null"));
    }


    @Test
    public void testLoadNullValueUseOptional() {
        CacheLoader<String, Optional<Employee>> loader = new CacheLoader<String, Optional<Employee>>() {
            @Override
            public Optional<Employee> load(String key) throws Exception {
                return key.equals("null") ? Optional.fromNullable(null)
                    : Optional.fromNullable(new Employee(key, key, key));
            }
        };
        LoadingCache<String, Optional<Employee>> cache = CacheBuilder.newBuilder().build(loader);

        Assert.assertNotNull(cache.getUnchecked("Jack"));
        // key为null返回null
        Assert.assertNull(cache.getUnchecked("null").orNull());

        // 默认值为default
        Employee def = cache.getUnchecked("null").or(new Employee("default", "default", "default"));
        Assert.assertEquals(def.getName().length(), 7);
    }
}