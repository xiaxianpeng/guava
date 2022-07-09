package com.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 00:39
 *
 * 基于容量的回收（size-based eviction），有两种方式，接近最大的size或weight时回收：
 * 基于maximumSize(long)：一个数据项占用一个size单位，适用于value是固定大小的情况
 * 基于maximumWeight(long)：对不同的数据项计算weight，适用于value不定大小的情况，比如value为Map类型时，可以把map.size()作为weight
 */
public class SizeBasedEvictionTest {

    @Test
    public void testBasic() throws ExecutionException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(30, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Employee>() {
                @Override
                public Employee load(String s) throws Exception {
                    return findEmployeeByName(s);
                }
            });

        Employee employee = cache.get("a");
        Assert.assertNotNull(employee);
        employee = cache.get("a");
        Assert.assertNotNull(employee);
    }


    /**
     * size驱逐策略
     */
    @Test
    public void testEvictionBySize() {
        CacheLoader<String, Employee> cacheLoader = createCacheLoader();
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .build(cacheLoader);

        cache.getUnchecked("Alex");
        cache.getUnchecked("Jack");
        cache.getUnchecked("Gavin");
        Assert.assertEquals(cache.size(), 3);
        cache.getUnchecked("Susan");
        Assert.assertEquals(cache.size(), 3);
        Assert.assertNull(cache.getIfPresent("Alex"));
    }

    /**
     * 权重驱逐策略
     */
    @Test
    public void testEvictionByWeight() {
        CacheLoader<String, Employee> cacheLoader = createCacheLoader();
        // 称
        Weigher<String, Employee> weigher = (key, employee) -> employee.getName().length() + employee.getEmpID().length() + employee.getDept().length();

        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
            .maximumWeight(45)
            .concurrencyLevel(1)
            .weigher(weigher)
            .build(cacheLoader);

        cache.getUnchecked("Gavin");
        cache.getUnchecked("Kevin");
        cache.getUnchecked("Allen");
        Assert.assertEquals(cache.size(), 3);
        Assert.assertNotNull(cache.getIfPresent("Gavin"));

        cache.getUnchecked("Jason");
        // lru
        Assert.assertNull(cache.getIfPresent("Kevin"));
    }

    private Employee findEmployeeByName(final String name) {
        System.out.println("findEmployeeByName: " + name);
        return new Employee(name, name, name);
    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String name) throws Exception {
                return findEmployeeByName(name);
            }
        };
    }
}
