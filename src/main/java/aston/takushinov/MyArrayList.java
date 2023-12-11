package aston.takushinov;

public class MyArrayList<T extends Comparable<T>> implements MyList<T>{
    private int capacity;
    private T[] elements;
    private int size;

    public MyArrayList() {
        this.capacity = 10;
        elements = (T[]) new Comparable[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        elements = (T[]) new Comparable[capacity];
    }

    @Override
    public boolean add(T element) {
        if (size>= elements.length) {
           int newSize = size * 2;
            T[] newElements = (T[]) new Comparable[newSize];
            for (int i = 0; i <elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size++] = element;
        return true;
    }

    @Override
    public boolean addAll(MyList<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        return true;
    }

    @Override
    public T get(int i) {
        if (i<size)
            return elements[i];
        return null;
    }

    @Override
    public boolean remove(int elementId) {
        if (elementId>=size) {
            return false;
        }
        for (int i = elementId; i < size-1; i++) {
            elements[i] = elements[i+1];
        }
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
        while (!flag) {
            i--;
            flag=true;
            for (int j = 0; j < i; j++) {
                if (elements[j].compareTo(elements[j+1])>0) {
                    T temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                    flag = false;
                }
            }
        }

    }
}
