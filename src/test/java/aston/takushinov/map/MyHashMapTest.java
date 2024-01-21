package aston.takushinov.map;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(testMap.size(),1000);
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
        for (int i = 0; i <1000; i++) {
            Assert.assertNull(testMap.get(i));
        }
    }

    @Test
    public void keySet() {
    }

    @Test
    public void values() {
    }

    @Test
    public void entrySet() {
    }
}