package com.example.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 16:52
 */
public class ListsTest {

    /**
     * 笛卡尔积
     */
    @Test
    public void testCartesianProduct() {
        List<List<String>> result = Lists.cartesianProduct(
            Lists.newArrayList("1", "2"),
            Lists.newArrayList("A", "B")
        );
        System.out.println(result);
    }

    @Test
    public void testTransform() {
        ArrayList<String> sourceList = Lists.newArrayList("C", "Java", "Go");
        Lists.transform(sourceList, e -> e.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void testNewArrayListWithCapacity() {
        ArrayList<String> result = Lists.newArrayListWithCapacity(10);
        result.add("x");
        result.add("y");
        result.add("z");
        System.out.println(result);
    }

    @Test
    public void testNewArrayListWithExpectedSize() {
        Lists.newArrayListWithExpectedSize(5);
    }

    @Test
    public void testReverse() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3");
        Assert.assertEquals(Joiner.on(",").join(list), "1,2,3");

        List<String> result = Lists.reverse(list);
        Assert.assertEquals(Joiner.on(",").join(result), "3,2,1");
    }

    @Test
    public void testPartition() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4");
        List<List<String>> resut = Lists.partition(list, 30);
        System.out.println(resut.get(0));
    }
}
