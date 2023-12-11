package aston.takushinov;

public interface MyList<T> {
    boolean add(T element );
    boolean addAll(MyList<? extends T> element );
    T get(int i);
    boolean remove(T element);
}
