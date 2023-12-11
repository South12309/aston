package aston.takushinov;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyList<String> myList = new MyLinkedList<>();
        MyList<String> myList2 = new MyLinkedList<>();

        for (int i = 0; i < 30; i++) {
            myList.add(String.valueOf(i));
        }
        myList.remove(10);
        myList.remove(25);
        myList2.addAll(myList);
        for (int i = 0; i < myList2.size(); i++) {
            System.out.println(myList2.get(i));
        }
    }
}