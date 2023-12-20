package aston.takushinov.lists;

public interface MyList<T> {
    boolean add(T element );
    boolean addAll(MyList<? extends T> list );
    T get(int i);
    boolean set(int elementId, T element);
    boolean remove(int elementId);
    int size();
    void sort();
}
