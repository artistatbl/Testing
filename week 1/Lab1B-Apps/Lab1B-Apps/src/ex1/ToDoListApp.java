package ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    private ArrayList<String> toDoList;

    public ToDoListApp() {
        toDoList = new ArrayList<>();
    }

    public void addItem(String item) {
        toDoList.add(item);
    }

    public void removeItem(String item) {
        toDoList.remove(item);
    }

    public void displayList() {
        System.out.println("To-Do List:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println((i + 1) + ". " + toDoList.get(i));
        }
    }

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display List");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the item to add: ");
                    String itemToAdd = scanner.nextLine();
                    app.addItem(itemToAdd);
                    System.out.println("Item added: " + itemToAdd);
                    break;
                case 2:
                    System.out.print("Enter the item to remove: ");
                    String itemToRemove = scanner.nextLine();
                    app.removeItem(itemToRemove);
                    break;
                case 3:
                    app.displayList();
                    break;
                case 4:
                    System.out.println("Exiting To-Do List App. Goodbye!");
                    scanner.close();
                    break;
            }
        }
    }
}
