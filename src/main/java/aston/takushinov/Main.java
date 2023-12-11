package aston.takushinov;




public class Main {
    public static void main(String[] args) {
        MyList<Integer> myList = new MyLinkedList<>();
//        MyList<String> myList2 = new MyLinkedList<>();
//
//        for (int i = 0; i < 30; i++) {
//            myList.add(String.valueOf(i));
//        }
//        myList.remove(10);
//        myList.remove(25);
//        myList2.addAll(myList);
//        for (int i = 0; i < myList2.size(); i++) {
//            System.out.println(myList2.get(i));
//        }

        myList.add(10);
        myList.add(8);
        myList.add(1);
        myList.add(15);
        myList.add(3);
        myList.add(5);
//        myList.sort();
        MyLinkedList.sort(myList);
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}