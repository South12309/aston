package aston.takushinov.map;

import java.util.Collection;
import java.util.HashMap;
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
        return table[hash((K)key)]!=null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < size - 1; i++) {
            Node node = (Node)table[i];
            while (node.next!=null) {
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
        return getFindedOrLastNode(key).value;
    }

    @Override
    public V put(K key, V value) {
        if (containsKey(key)) {
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

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }
}
