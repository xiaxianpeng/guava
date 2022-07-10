package com.example.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 16:41
 */
public class ImmutableCollectionsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testOf() {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
        Assert.assertNotNull(list);
        //java.lang.UnsupportedOperationException
        list.add(4);
    }

    @Test
    public void testCopy() {
        Integer[] array = {1, 2, 3, 4, 5};
        System.out.println(ImmutableList.copyOf(array));
    }

    @Test
    public void testBuilder() {
        ImmutableList<Integer> list = ImmutableList.<Integer>builder()
            .add(1)
            .add(2, 3, 4)
            .addAll(Arrays.asList(5, 6))
            .build();
        System.out.println(list);
    }

    @Test
    public void testImmutableMap() {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder()
            .put("Oracle", "12c")
            .put("Mysql", "7.0")
            .build();
        System.out.println(map);
        Assert.assertThrows(UnsupportedOperationException.class, () -> map.put("SqlServer", "5.6"));
    }
}
