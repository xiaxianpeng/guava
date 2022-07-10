package com.example.guava.collections;

import com.google.common.collect.BoundType;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 17:51
 */
public class RangeTest {

    @Test
    public void testClosedRange() {
        Range<Integer> closedRange = Range.closed(0, 9);
        System.out.println(closedRange);
        Assert.assertTrue(closedRange.contains(5));
        Assert.assertEquals((int) closedRange.lowerEndpoint(), 0);
        Assert.assertEquals((int) closedRange.upperEndpoint(), 9);
    }

    @Test
    public void testOpenRange() {
        Range<Integer> openRange = Range.open(0, 9);
        System.out.println(openRange);
        Assert.assertTrue(openRange.contains(5));
        Assert.assertEquals((int) openRange.lowerEndpoint(), 0);
        Assert.assertEquals((int) openRange.upperEndpoint(), 9);
        Assert.assertFalse(openRange.contains(0));
        Assert.assertFalse(openRange.contains(9));
    }


    @Test
    public void testOpenClosedRange() {
        Range<Integer> openClosedRange = Range.openClosed(0, 9);
        System.out.println(openClosedRange);
        Assert.assertTrue(openClosedRange.contains(5));
        Assert.assertEquals((int) openClosedRange.lowerEndpoint(), 0);
        Assert.assertEquals((int) openClosedRange.upperEndpoint(), 9);
        Assert.assertFalse(openClosedRange.contains(0));
        Assert.assertTrue(openClosedRange.contains(9));
    }


    @Test
    public void testClosedOpenRange() {
        Range<Integer> closedOpenRange = Range.closedOpen(0, 9);
        System.out.println(closedOpenRange);
        Assert.assertTrue(closedOpenRange.contains(5));
        Assert.assertEquals((int) closedOpenRange.lowerEndpoint(), 0);
        Assert.assertEquals((int) closedOpenRange.upperEndpoint(), 9);
        Assert.assertTrue(closedOpenRange.contains(0));
        Assert.assertFalse(closedOpenRange.contains(9));
    }

    @Test
    public void testGreaterThan() {
        Range<Integer> range = Range.greaterThan(10);
        System.out.println(range);
        Assert.assertFalse(range.contains(10));
        Assert.assertTrue(range.contains(Integer.MAX_VALUE));
    }

    @Test
    public void testMapRange() {
        TreeMap<String, Integer> treeMap = Maps.newTreeMap();
        treeMap.put("C", 1);
        treeMap.put("Java", 2);
        treeMap.put("Go", 3);
        treeMap.put("Rust", 4);
        System.out.println(treeMap);
        NavigableMap<String, Integer> result = Maps.subMap(treeMap, Range.openClosed("C", "Go"));
        System.out.println(result);
    }

    @Test
    public void testOtherMethod() {
        Range<Integer> atLeastRange = Range.atLeast(2);
        System.out.println(atLeastRange);
        Assert.assertTrue(atLeastRange.contains(2));

        System.out.println(Range.lessThan(10));
        System.out.println(Range.atMost(5));
        System.out.println(Range.all());
        System.out.println(Range.downTo(10, BoundType.CLOSED));
        System.out.println(Range.upTo(10, BoundType.CLOSED));
    }

    @Test
    public void testRangeMap() {
        TreeRangeMap<Integer, String> gradeScale = TreeRangeMap.create();
        gradeScale.put(Range.closed(0, 60), "E");
        gradeScale.put(Range.closed(61, 70), "D");
        gradeScale.put(Range.closed(71, 80), "C");
        gradeScale.put(Range.closed(81, 90), "B");
        gradeScale.put(Range.closed(91, 100), "A");
        Assert.assertEquals(gradeScale.get(77), "C");
    }
}
