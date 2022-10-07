package InterfacesAndAbstraction.Exercise.CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] inputToAdd = scan.nextLine().split("\\s+");
        int numberOfRemoves = Integer.parseInt(scan.nextLine());

        addAndPrint(inputToAdd, addCollection);
        addAndPrint(inputToAdd, addRemoveCollection);
        addAndPrint(inputToAdd, myList);

        removeAndPrint(numberOfRemoves,addRemoveCollection);
        removeAndPrint(numberOfRemoves,myList);
    }

    private static void removeAndPrint(int numberOfRemoves, AddRemovable collection) {
        for (int i = 0; i < numberOfRemoves; i++) {
            System.out.print(collection.remove()+" ");
        }
        System.out.println();
    }

    private static void addAndPrint(String[] inputToAdd, Addable collection) {
        for (String s : inputToAdd) {
            System.out.print(collection.add(s)+" ");
        }
        System.out.println();
    }
}
