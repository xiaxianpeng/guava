package com.example.guava.collections;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 17:05
 */
public class MapsTest {

    @Test
    public void testCreate() {
        ArrayList<String> valueList = Lists.newArrayList("1", "2", "3");
        ImmutableMap<String, String> map = Maps.uniqueIndex(valueList, v -> v + "_key");
        System.out.println(map);

        Map<String, String> newMap = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        System.out.println(newMap);
    }

    @Test
    public void testTransform() {
        Map<String, String> map = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        Map<String, String> newMap = Maps.transformValues(map, v -> v + "_transform");
        System.out.println(newMap);
        Assert.assertTrue(newMap.containsValue("1_value_transform"));
    }

    @Test
    public void testFilter() {
        Map<String, String> map = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        Map<String, String> newMap = Maps.filterKeys(map, k -> Lists.newArrayList("1", "2").contains(k));
        Assert.assertFalse(newMap.containsKey("3"));
    }
}
