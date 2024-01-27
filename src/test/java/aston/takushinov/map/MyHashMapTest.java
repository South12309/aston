package aston.takushinov.map;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyHashMapTest {
    MyHashMap<Integer, String> testMap = new MyHashMap<>();

    @Before
    public void setUp() throws Exception {

        for (int i = 0; i < 1000; i++) {
            testMap.put(i, i + "-ая строкая");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void size() {
        Assert.assertEquals(testMap.size(), 1000);
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(!testMap.isEmpty());
    }

    @Test
    public void containsKey() {
        Assert.assertTrue(testMap.containsKey(50));
        Assert.assertFalse(testMap.containsKey(5000));
    }

    @Test
    public void containsValue() {
        Assert.assertTrue(testMap.containsValue("923-ая строкая"));
    }

    @Test
    public void get() {
        Assert.assertEquals("923-ая строкая", testMap.get(923));
        Assert.assertNull(testMap.get(1001));
    }

    @Test
    public void put() {
    }

    @Test
    public void remove() {
        testMap.remove(50);
        Assert.assertNull(testMap.get(50));
    }

    @Test
    public void clear() {
        testMap.clear();
        for (int i = 0; i < 1000; i++) {
            Assert.assertNull(testMap.get(i));
        }
    }

    @Test
    public void keySet() {
        Set<Integer> testKeySet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            testKeySet.add(i);
        }
        Assert.assertEquals(testKeySet, testMap.keySet());
    }

    @Test
    public void values() {
        Collection<String> testValues = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testValues.add(i + "-ая строкая");
        }

        List<String> valuesSorted = testMap.values().stream().sorted().toList();
        List<String> testValuesSorted = testValues.stream().sorted().toList();

        Assert.assertEquals(testValuesSorted, valuesSorted);
    }

    @Test
    public void entrySet() {
        HashSet<Map.Entry<Integer, String>> resultTest = new HashSet<>();
        for (int i = 0; i < testMap.size(); i++) {
            resultTest.add(new MyHashMap.Entry(i, i + "-ая строкая"));
        }
        HashSet<Map.Entry<Integer, String>> result = new HashSet<>();
        for (int i = 0; i < testMap.size(); i++) {
            result.add(new MyHashMap.Entry(i, testMap.get(i)));
        }
        Assert.assertEquals(resultTest, result);
    }
}