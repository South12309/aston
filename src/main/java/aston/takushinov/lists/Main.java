package aston.takushinov.lists;




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

// //         MyList<Integer> myList = new MyLinkedList<>();
// //        MyList<String> myList2 = new MyLinkedList<>();
// //
// //        for (int i = 0; i < 30; i++) {
// //            myList.add(String.valueOf(i));
// //        }
// //        myList.remove(10);
// //        myList.remove(25);
// //        myList2.addAll(myList);
// //        for (int i = 0; i < myList2.size(); i++) {
// //            System.out.println(myList2.get(i));
// //        }

// //         myList.add(10);
// //         myList.add(8);
// //         myList.add(1);
// //         myList.add(15);
// //         myList.add(3);
// //         myList.add(5);
// // //        myList.sort();
// //         MyLinkedList.sort(myList);
// //         for (int i = 0; i < myList.size(); i++) {
// //             System.out.println(myList.get(i));

        }
    }
}