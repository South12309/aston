package aston.takushinov;

public interface MyList<T> {
    boolean add(T element );
    boolean addAll(MyList<? extends T> list );
    T get(int i);
    boolean remove(int elementId);
    int size();
    void sort();
}
