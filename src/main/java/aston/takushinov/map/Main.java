package aston.takushinov.map;

public class Main {


    public static void main(String[] args) {
        MyHashMap<Integer, String> testMap = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            testMap.put(i, i + "-ая строкая");
        }

        System.out.println(testMap.containsKey(999));
    }
}
