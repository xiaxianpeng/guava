package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 11:44
 * 统计
 */
public class CacheStatTest {

    @Test
    public void testCacheStat() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(5)
            .recordStats()
            .build(loader);

        assertCache(cache);
    }

    @Test
    public void testCacheSpec() {
        String spec = "maximumSize=5,recordStats";
        CacheBuilderSpec builderSpec = CacheBuilderSpec.parse(spec);
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.from(builderSpec).build(loader);

        assertCache(cache);
    }

    private void assertCache(LoadingCache<String, String> cache) {
        Assert.assertEquals(cache.getUnchecked("jack"), "JACK");
        CacheStats stats = cache.stats();
        System.out.println(stats.hashCode());
        Assert.assertEquals(stats.hitCount(), 0L);
        Assert.assertEquals(stats.missCount(), 1L);

        Assert.assertEquals(cache.getUnchecked("jack"), "JACK");

        stats = cache.stats();
        System.out.println(stats.hashCode());
        Assert.assertEquals(stats.hitCount(), 1L);
        Assert.assertEquals(stats.missCount(), 1L);

        System.out.println(stats.missRate());
        System.out.println(stats.hitRate());
    }
}
