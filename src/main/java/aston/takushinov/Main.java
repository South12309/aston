package aston.takushinov;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> myArrayList = new MyArrayList<>();
//        MyList<String> myArrayList2 = new MyArrayList<>();
//
//        for (int i = 0; i < 30; i++) {
//            myArrayList.add(String.valueOf(i));
//        }
//        myArrayList.remove(10);
//        myArrayList.remove(25);
//        myArrayList2.addAll(myArrayList);
//        for (int i = 0; i < myArrayList2.size(); i++) {
//            System.out.println(myArrayList2.get(i));
//        }
        myArrayList.add(10);
        myArrayList.add(8);
        myArrayList.add(1);
        myArrayList.add(15);
        myArrayList.add(3);
        myArrayList.add(5);
      //  myArrayList.sort();
        MyArrayList.sort(myArrayList);
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }
    }
}