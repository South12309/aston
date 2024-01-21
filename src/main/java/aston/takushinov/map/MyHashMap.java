package aston.takushinov.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    private int M = 97;
    private int size = 0;
    private Object[] table = new Object[M];

    private class Node {
        K key;
        V value;
        Node next;

        private Node(K key, V value, Node next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }
    }

    private class Entry<K,V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        int ind = hash((K) key);
        Node node = (Node)table[ind];
        if (node ==null){
             return false;
         }
         while (node!=null) {
             if (node.key.equals(key)) {
                 return true;
             }
             node=node.next;
         }
         return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            Node node = (Node)table[i];
            while (node!=null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node=node.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Node findedOrLastNode = getFindedOrLastNode(key);
        if (findedOrLastNode==null) {
            return null;
        }
        if (findedOrLastNode.key.equals(key)) {
            return findedOrLastNode.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int ind = hash((K) key);
        Node nodeFirst = (Node)table[ind];
        if (nodeFirst!=null) {
            Node node = getFindedOrLastNode(key);
            if (node.key.equals(key)) {
                node.value = value;
            } else {
                node.next = new Node(key,value, null);
                size++;
            }

        } else {
            table[hash(key)] = new Node(key,value, null);
            size++;
        }
        return value;
    }


    private Node getFindedOrLastNode(Object key) {
        Node node = (Node)table[hash((K)key)];
        if (node==null) {
            return null;
        }
        while (node.next!=null) {
            if (node.key.equals(key)) {
               return node;
            }
            node=node.next;
        }
        return node;
    }

    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            int ind = hash((K) key);
            Node node = (Node) table[ind];
            if (node.key.equals(key)) {
                size--;
                if (node.next == null) {
                    table[ind] = null;
                    return node.value;
                } else {
                    table[ind] = node.next;
                    return node.value;
                }
            } else {
                Node nextNode = node.next;
                while (nextNode.next != null) {
                    if (nextNode.key.equals(key)) {
                        node.next = nextNode.next;
                        size--;
                        return nextNode.value;
                    }
                }
            }

        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K,? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i]=null;
        }

    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            Node node = (Node)table[i];
            while (node!=null) {
                result.add(node.key);
                node=node.next;
            }
        }
        return result;
    }

    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            Node node = (Node)table[i];
            while (node!=null) {
                result.add(node.value);
                node=node.next;
            }
        }
        return result;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        HashSet<Map.Entry<K, V>> result = new HashSet<>();
        for (int i = 0; i < size - 1; i++) {
            Node node = (Node)table[i];
            while (node!=null) {
                result.add(new Entry(node.key, node.value));
                node=node.next;
            }
        }
        return null;
    }

    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }
}
