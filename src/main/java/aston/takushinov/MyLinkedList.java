package aston.takushinov;

import java.util.Collection;
import java.util.LinkedList;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    public MyLinkedList() {
    }

    public MyLinkedList(Collection<? extends T> collection) {
        for (T t : collection) {
            add(t);
        }
    }

    @Override
    public boolean add(T element) {
        if (firstNode == null) {
            firstNode = new Node(null, element, null);
            lastNode = firstNode;
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
    public boolean addAll(MyList<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        return true;
    }

    @Override
    public T get(int elementId) {
        Node<T> node = getNode(elementId);
        if (node == null)
            return null;
        return node.item;
    }

    @Override
    public boolean set(int elementId, T element) {
        Node<T> node = getNode(elementId);
        if (node == null)
            return false;
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;
        Node newNode = new Node(prevNode, element, nextNode);
        if (prevNode != null) {
            prevNode.next = newNode;
        } else {
            firstNode = newNode;
        }
        if (nextNode != null) {
            nextNode.prev = newNode;
        } else {
            lastNode = newNode;
        }
        return true;
    }

    private Node<T> getNode(int elementId) {
        if (elementId >= size)
            return null;
        if (elementId == 0) {
            return firstNode;
        }
        if (elementId == size - 1)
            return lastNode;

        int i = 0;
        Node<T> currentNode = firstNode;
        while (!(i == elementId)) {
            currentNode = currentNode.next;
            i++;
        }
        return currentNode;
    }

    @Override
    public boolean remove(int elementId) {
        Node<T> nodeForRemove = getNode(elementId);
        nodeForRemove.prev.next = nodeForRemove.next;
        nodeForRemove.next.prev = nodeForRemove.prev;
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        int i = size;
        boolean flag = false;
        Node<T> currNode;
        Node<T> nextNode;
        Node<T> nextNextNode;
        Node<T> prevNode;
        //TODO need to realize using new method set()
        while (!flag) {
            currNode = firstNode;
            i--;
            flag = true;
            for (int j = 0; j < i; j++) {
                if (currNode.next == null) {
                    lastNode = currNode;
                    break;
                }
                if (currNode.item.compareTo(currNode.next.item) > 0) {
                    nextNode = currNode.next;
                    nextNextNode = nextNode.next;
                    if (currNode != firstNode) {
                        prevNode = currNode.prev;
                        prevNode.next = nextNode;
                    } else {
                        prevNode = null;
                        firstNode = nextNode;
                    }
                    nextNode.prev = prevNode;
                    nextNode.next = currNode;
                    currNode.prev = nextNode;
                    currNode.next = nextNextNode;
                    if (nextNextNode != null) {
                        nextNextNode.prev = currNode;
                    } else {
                        lastNode = currNode;
                    }
                    flag = false;
                } else {
                    currNode = currNode.next;
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void sort(MyList<T> list) {
        int i = list.size();
        boolean flag = false;
        while (!flag) {
            i--;
            flag = true;
            for (int j = 0; j < i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    flag = false;
                }
            }
        }

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
