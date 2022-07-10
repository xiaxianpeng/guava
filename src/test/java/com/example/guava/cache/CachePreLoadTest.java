package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 11:30
 *
 * 预加载
 */
public class CachePreLoadTest {

    /**
     * 预加载
     */
    @Test
    public void testCachePreLoad() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        HashMap<String, String> preData = new HashMap<String, String>() {{
            put("jack", "JACK");
            put("mike", "mike");
        }};

        cache.putAll(preData);
        Assert.assertEquals(cache.size(), 2);
        Assert.assertEquals(cache.getUnchecked("jack"), "JACK");
        Assert.assertEquals(cache.getUnchecked("mike"), "mike");
    }
}
