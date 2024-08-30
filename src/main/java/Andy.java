import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Andy {

    private static List<String> itemList = new ArrayList<>(); // List to store items

    public static void main(String[] args) {
        greetUser(); // method to greet the user

        Scanner scanner = new Scanner(System.in); // Use the imported Scanner class
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.startsWith("list")) {
                handleListInput(input);
            } else {
                echo(input); // Call echo method to repeat the input
            }
        }

        exit();
    }

    private static void greetUser() {
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm ANDY");
        System.out.println("What can I do for you?");
        System.out.println("Type 'list' followed by an item to add it to the list.");
        System.out.println("Type 'list show' to display all items in the list.");
        System.out.println("_______________________________________");
    }

    private static void handleListInput(String input) {
        String[] parts = input.split(" ", 2); // Split the input into command and item

        if (parts.length > 1) {
            String command = parts[1];

            if (command.equals("show")) {
                showList();
            } else {
                addItemToList(command);
            }
        } else {
            System.out.println("Please provide a valid list command or item.");
        }
    }

    private static void addItemToList(String item) {
        itemList.add(item);
        System.out.println("Item added to the list: " + item);
    }

    private static void showList() {
        System.out.println("_______________________________________");
        System.out.println("Items in your list:");
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (String item : itemList) {
                System.out.println("- " + item);
            }
        }
        System.out.println("_______________________________________");
    }

    private static void echo(String input) {
        System.out.println("You said: " + input);
    }

    private static void exit() {
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }
}