package com.example.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 13:14
 *
 *
 */
public class FluentIterableTest {

    private FluentIterable<String> build() {
        ArrayList<String> list = Lists.newArrayList("C", "Java", "Go", "Python");
        return FluentIterable.from(list);
    }

    @Test
    public void testFilter() {
        FluentIterable<String> fit = build();
        Assert.assertEquals(fit.size(), 4);

        FluentIterable<String> result = fit.filter(e -> e != null && e.length() > 2);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void testAppend() {

        FluentIterable<String> fit = build();
        ArrayList<String> append = Lists.newArrayList("APPEND");
        Assert.assertEquals(fit.size(), 4);

        FluentIterable<String> appendFit = fit.append(append);
        Assert.assertEquals(appendFit.size(), 5);
        Assert.assertTrue(appendFit.contains("APPEND"));

        appendFit = appendFit.append("append");
        Assert.assertEquals(appendFit.size(), 6);
        Assert.assertTrue(appendFit.contains("append"));
        Assert.assertTrue(appendFit.contains("C"));

    }

    @Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        boolean result = fit.anyMatch(e -> e != null && e.length() >= 4);
        Assert.assertTrue(result);

        result = fit.anyMatch(e -> e != null && e.length() == 4);
        Assert.assertTrue(result);

        Optional<String> optional = fit.firstMatch(e -> e != null && e.length() == 6);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "Python");
    }

    @Test
    public void testFirst$Last() {
        FluentIterable<String> fit = build();
        Optional<String> firstOptional = fit.first();
        Assert.assertTrue(firstOptional.isPresent());
        Assert.assertEquals(firstOptional.get(), "C");

        Optional<String> lastOptional = fit.last();
        Assert.assertTrue(lastOptional.isPresent());
        Assert.assertEquals(lastOptional.get(), "Python");
    }

    @Test
    public void testLimit() {
        FluentIterable<String> fit = build();
        FluentIterable<String> limit = fit.limit(3);
        System.out.println(limit);
        Assert.assertTrue(limit.contains("C"));

        limit = fit.limit(300);
        System.out.println(limit);
        Assert.assertTrue(limit.contains("Python"));
    }

    @Test
    public void testCopyIn() {
        FluentIterable<String> fit = build();
        ArrayList<String> list = Lists.newArrayList("Rust");
        ArrayList<String> result = fit.copyInto(list);

        Assert.assertEquals(result.size(), 5);
        Assert.assertTrue(result.contains("Rust"));
    }

    @Test
    public void testCycle() {
        FluentIterable<String> fit = build();
        FluentIterable<String> cycle = fit.cycle().limit(20);
        cycle.forEach(System.out::println);
    }

    @Test
    public void testTransform() {
        FluentIterable<String> fit = build();
        fit.transform(e -> e.length()).forEach(System.out::println);
    }

    @Test
    public void testTransformAndConcat() {
        FluentIterable<String> fit = build();
        ArrayList<Integer> list = Lists.newArrayList(1);
        FluentIterable<Integer> result = fit.transformAndConcat(e -> list);
        result.forEach(System.out::println);
    }

    @Test
    public void testTransformAndConcatInAction() {
        ArrayList<Integer> cTypes = Lists.newArrayList(1, 2);
        FluentIterable.from(cTypes).transformAndConcat(this::search)
            .forEach(System.out::println);
    }

    @Test
    public void testJoin() {
        FluentIterable<String> fit = build();
        String result = fit.join(Joiner.on(","));
        Assert.assertEquals(result, "C,Java,Go,Python");
    }

    private List<Customer> search(int type) {
        if (type == 1) {
            return Lists.newArrayList(new Customer(type, "A"), new Customer(type, "B"));
        } else {
            return Lists.newArrayList(new Customer(type, "X"), new Customer(type, "Y"), new Customer(type, "Z"));
        }
    }

    class Customer {

        final int type;
        final String name;

        public Customer(int type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                "type=" + type +
                ",name=" + name +
                "}";
        }
    }
}
