package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 11:13
 * 删除通知
 */
public class CacheRemoveNotificationTest {


    @Test
    public void testCacheRemovedNotification() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        RemovalListener<String, String> listener = notification -> {
            if (notification.wasEvicted()) {
                RemovalCause cause = notification.getCause();
                // 由于容量缓存被删除
                Assert.assertEquals(cause, RemovalCause.SIZE);
                Assert.assertEquals(notification.getKey(), "a");
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .removalListener(listener)
            .build(loader);

        cache.getUnchecked("a");
        cache.getUnchecked("b");
        cache.getUnchecked("c");
        cache.getUnchecked("d");
    }
}
