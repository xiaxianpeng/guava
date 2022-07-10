package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 11:13
 * * Guava Cache之Refresh
 */
public class RefreshTest {

    /**
     * 刷新
     */
    @Test
    public void testCacheRefresh() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CacheLoader<String, Long> cacheLoader = CacheLoader.from(k -> {
            counter.incrementAndGet();
            return System.currentTimeMillis();
        });

        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
            .refreshAfterWrite(2, TimeUnit.SECONDS)
            .build(cacheLoader);

        Long result1 = cache.getUnchecked("Jack");
        TimeUnit.SECONDS.sleep(3);
        Long result2 = cache.getUnchecked("Jack");

        // 不刷新
        //Assert.assertEquals(counter.get(), 1);
        // 刷新，val不相等
        Assert.assertNotEquals(result1, result2);

    }

}
