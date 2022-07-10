package com.example.guava.collections;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 17:16
 */
public class MultimapTest {

    @Test
    public void testBasic() {
        LinkedListMultimap<String, String> multipleMap = LinkedListMultimap.create();
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        Assert.assertEquals(hashMap.size(), 1);

        multipleMap.put("1","1");
        multipleMap.put("1","2");
        Assert.assertEquals(multipleMap.size(), 2);
        System.out.println(multipleMap.get("1"));
    }
}
