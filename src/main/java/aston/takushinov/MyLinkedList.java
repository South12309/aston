package aston.takushinov;

import java.util.LinkedList;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;
    @Override
    public boolean add(T element) {
        if (firstNode==null) {
            firstNode = new Node(null, element, null);
            size++;
            return true;
        } else {
            lastNode.next = new Node<>(lastNode, element, null);
            lastNode = lastNode.next;
            size++;
            return true;
        }
    }

    @Override
    public boolean addAll(MyList<? extends T> element) {
        return false;
    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
