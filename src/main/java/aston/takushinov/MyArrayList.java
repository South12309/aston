package aston.takushinov;

public class MyArrayList<T> implements MyList<T>{
    private int capacity;
    private T[] elements;
    private int size;

    public MyArrayList() {
        this.capacity = 10;
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean add(T element) {
        if (size>= elements.length) {
           int newSize = size * 2;
            T[] newElements = (T[]) new Object[newSize];
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
}
