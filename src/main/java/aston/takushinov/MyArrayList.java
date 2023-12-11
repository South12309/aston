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
    public boolean addAll(MyList<? extends T> element) {
        return false;
    }

    @Override
    public T get(int i) {
        if (i<size)
            return elements[i];
        return null;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }
}
