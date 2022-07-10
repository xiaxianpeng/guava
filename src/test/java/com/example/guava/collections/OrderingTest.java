package com.example.guava.collections;

import com.google.common.collect.Ordering;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 17:42
 */
public class OrderingTest {

    @Test
    public void testJDKOrder() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test(expected = NullPointerException.class)
    public void testJDKOrderIssue() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        // NullPointerException
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testOrderNaturalByNullFirst() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list, Ordering.natural().nullsFirst());
        System.out.println(list);
    }

    @Test
    public void testOrderNaturalByNullLast() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list, Ordering.natural().nullsLast());
        System.out.println(list);
    }

    @Test
    public void testOrderNatural() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list);
        Assert.assertTrue(Ordering.natural().isOrdered(list));
        System.out.println(list);
    }

    @Test
    public void testOrderReverse() {
        List<Integer> list = Arrays.asList(1, 5, 3, 8, 2);
        System.out.println(list);
        Collections.sort(list,Ordering.natural().reverse());
        System.out.println(list);
    }
}
