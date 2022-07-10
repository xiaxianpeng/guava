package com.example.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table.Cell;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xianpeng.xia
 * on 2022/7/10 19:11
 */
public class TableTest {

    @Test
    public void test() {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("Language", "Java", "1.8");
        table.put("Language", "Scala", "2.3");
        table.put("Database", "Oracle", "12C");
        table.put("Database", "Mysql", "7.0");
        System.out.println(table);

        Map<String, String> language = table.row("Language");
        Assert.assertTrue(language.containsKey("Java"));
        Assert.assertEquals(table.row("Language").get("Java"), "1.8");

        Map<String, String> result = table.column("Java");
        System.out.println(result);

        Set<Cell<String, String, String>> cells = table.cellSet();
        System.out.println(cells);
    }
}
