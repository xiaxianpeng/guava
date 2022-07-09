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
 *
 * expireAfterAccess(long, TimeUnit)：缓存项在给定时间内没有被读/写，则回收。
 * expireAfterWrite(long, TimeUnit)：缓存项在给定时间内没有被写访问（创建或覆盖），则回收。
 */
public class TimedEvictionTest {

    /**
     * TTL -> time to live
     * Access time => Write/Update/Read
     */
    @Test
    public void testEvictionByAccessTime() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .build(createCacheLoader());

        Assert.assertNotNull(cache.getUnchecked("Roger"));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertNull(cache.getIfPresent("Roger"));

        Assert.assertNotNull(cache.getUnchecked("Jack"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNotNull(cache.getIfPresent("Jack"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNotNull(cache.getIfPresent("Jack"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNotNull(cache.getIfPresent("Jack"));

    }

    /**
     * Write time => write/update
     */
    @Test
    public void testEvictionByWriteTime() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build(createCacheLoader());

        Assert.assertNotNull(cache.getUnchecked("Roger"));
        Assert.assertNotNull(cache.getUnchecked("Jack"));

        TimeUnit.SECONDS.sleep(1);
        Assert.assertNotNull(cache.getIfPresent("Jack"));
        TimeUnit.MILLISECONDS.sleep(990);
        Assert.assertNotNull(cache.getIfPresent("Jack"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNull(cache.getIfPresent("Jack"));
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
