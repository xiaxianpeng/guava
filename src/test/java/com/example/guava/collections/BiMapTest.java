package com.example.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 12:03
 * BiMap是一种特殊的映射其保持映射，同时确保没有重复的值是存在于该映射和一个值可以安全地用于获取键背面的倒数映射。
 */
public class BiMapTest {

    @Test
    public void testCreateAndPut() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("1", "3");

        Assert.assertTrue(biMap.containsKey("1"));
        Assert.assertEquals(biMap.size(), 1);
        // value already present: 3
        Assert.assertThrows(IllegalArgumentException.class, () -> biMap.put("2", "3"));
    }

    @Test
    public void testBiMapInverse() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("2", "3");
        biMap.put("3", "4");

        Assert.assertTrue(biMap.containsKey("1"));
        Assert.assertTrue(biMap.containsKey("2"));
        Assert.assertTrue(biMap.containsKey("3"));
        Assert.assertEquals(biMap.size(), 3);

        BiMap<String, String> inverseKey = biMap.inverse();
        Assert.assertTrue(inverseKey.containsKey("2"));
        Assert.assertTrue(inverseKey.containsKey("3"));
        Assert.assertTrue(inverseKey.containsKey("4"));
        Assert.assertEquals(inverseKey.size(), 3);

    }

    @Test
    public void testCreateAndForcePut() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        Assert.assertTrue(biMap.containsKey("1"));

        biMap.forcePut("2", "2");

        Assert.assertFalse(biMap.containsKey("1"));
        Assert.assertTrue(biMap.containsKey("2"));

    }
}
