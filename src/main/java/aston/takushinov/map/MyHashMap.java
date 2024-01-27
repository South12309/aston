package aston.takushinov.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Реализация словаря. Для хранения пары ключ - значение используется массив в котором хранится односвязанный список.
 *
 * @param <K> - объект-ключ
 * @param <V> - объект-значение
 */
public class MyHashMap<K, V> implements Map<K, V> {
    private int CAPACITY = 97;
    private int size = 0;
    private Object[] table = new Object[CAPACITY];

    /**
     * Односвязанный список, который хранится в массиве.
     * Содержит в себе ключи, значение и ссылку на следующий элемент списка
     */
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

    /**
     * Реализация записи пары ключ - значение для итерации
     *
     * @param <K> - объект-ключ
     * @param <V> - объект-значение
     */
    public static class Entry<K, V> implements Map.Entry<K, V> {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    /**
     * Возвращает размер словаря
     *
     * @return цифровое значение размера
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка на пустоту словаря
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет наличие ключа в словаре.
     *
     * @param key объект ключа, который надо проверить на наличии в словаре
     * @return истина или ложь
     */
    @Override
    public boolean containsKey(Object key) {
        int ind = hash((K) key);
        Node node = (Node) table[ind];
        if (node == null) {
            return false;
        }
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Проверяет наличие значения в словаре
     *
     * @param value объект значения, который надо проверить на наличии в словаре
     * @return истина или ложь
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            Node node = (Node) table[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Возвращает значение по ключу.
     *
     * @param key объект-ключ по которому будет получено значение из словаря.
     * @return значение словаря по заданному ключу
     */
    @Override
    public V get(Object key) {
        Node findedOrLastNode = getFindedOrLastNode(key);
        if (findedOrLastNode == null) {
            return null;
        }
        if (findedOrLastNode.key.equals(key)) {
            return findedOrLastNode.value;
        }
        return null;
    }

    /**
     * Добавляет в словарь пару ключ - значение
     *
     * @param key   объект-ключ
     * @param value объект-значение
     * @return вернет значение, если добавление будет успешным
     */
    @Override
    public V put(K key, V value) {
        int ind = hash((K) key);
        Node nodeFirst = (Node) table[ind];
        if (nodeFirst != null) {
            Node node = getFindedOrLastNode(key);
            if (node.key.equals(key)) {
                node.value = value;
            } else {
                node.next = new Node(key, value, null);
                size++;
            }

        } else {
            table[hash(key)] = new Node(key, value, null);
            size++;
        }
        return value;
    }

    /**
     * Возвращает элемент списка с текущим ключом или последний элемент списка в данной ячейке массива, если ключ не найден.
     *
     * @param key ключ по которому проходит поиск
     * @return Node с ключом или последняя Node в массиве из ячейки с индексом хэщ ключа
     */
    private Node getFindedOrLastNode(Object key) {
        Node node = (Node) table[hash((K) key)];
        if (node == null) {
            return null;
        }
        while (node.next != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return node;
    }

    /**
     * Удаляет элемент словаря по ключу
     *
     * @param key ключ-объект по которому удаляется запись.
     * @return значение удаленной записи из словаря
     */
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

    /**
     * Добавляет в словарь содержимое другого словаря
     *
     * @param m словарь из которого должны быть добавлены записи в текущий словарь.
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }

    }

    /**
     * Очищает словарь.
     */
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }

    }

    /**
     * Возвращает множество ключей словаря.
     *
     * @return множество ключей словаря
     */
    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            Node node = (Node) table[i];
            while (node != null) {
                result.add(node.key);
                node = node.next;
            }
        }
        return result;
    }

    /**
     * Возвращает коллекцию значений словаря
     *
     * @return коллекция значения словаря
     */
    @Override
    public Collection<V> values() {
        List<V> result = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            Node node = (Node) table[i];
            while (node != null) {
                result.add(node.value);
                node = node.next;
            }
        }
        return result;
    }

    /**
     * Возвращает множество ключ-значение словаря
     *
     * @return множество ключ-значение словаря
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        HashSet<Map.Entry<K, V>> result = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            Node node = (Node) table[i];
            while (node != null) {
                result.add(new Entry(node.key, node.value));
                node = node.next;
            }
        }
        return result;
    }

    /**
     * Вычисляет хэш ключа.
     *
     * @param key ключ по которому вычисляется хэш
     * @return целое число являющееся хэшом ключа
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % CAPACITY;
    }
}
