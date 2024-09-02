import java.util.Hashtable;
import java.util.Scanner;

public class DataStorage {

    public static void storeData(Scanner scanner) {
        System.out.println("List your plans one by one");
        Hashtable<String, Boolean> storedItems = new Hashtable<>();
        boolean exit = false;

        while (!exit) {
            String line = scanner.nextLine();
            exit = processInput(line, scanner, storedItems);
        }

        Initializer.initialise(scanner, new LLMChat());
    }

    private static boolean processInput(String line, Scanner scanner, Hashtable<String, Boolean> storedItems) {
        if (line.equalsIgnoreCase("bye")) {
            return true;  // Exit the loop
        } else if (line.equalsIgnoreCase("list")) {
            listItems(storedItems);
        } else if (line.startsWith("unmark")) {
            handleUnmark(line, scanner, storedItems);
        } else if (line.startsWith("mark")) {
            handleMark(line, storedItems);
        } else {
            addItem(line, storedItems);
        }
        return false;  // Continue the loop
    }

    private static void listItems(Hashtable<String, Boolean> storedItems) {
        for (String key : storedItems.keySet()) {
            if (storedItems.get(key)) {
                System.out.println("[X] " + key);
            } else {
                System.out.println("[ ] " + key);
            }
        }
    }

    private static void handleUnmark(String line, Scanner scanner, Hashtable<String, Boolean> storedItems) {
        String item = line.substring(7);
        if (storedItems.containsKey(item)) {
            storedItems.put(item, false);
            System.out.println("I have unmarked the task.");
            System.out.println("[ ] " + item);
        } else {
            promptToAddItem(scanner, storedItems, item);
        }
    }

    private static void promptToAddItem(Scanner scanner, Hashtable<String, Boolean> storedItems, String item) {
        System.out.println(item + " is not in your list yet. Do you wish to add it to the list? yes/no");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            storedItems.put(item, false);
            System.out.println("Added: " + item);
        } else {
            System.out.println("Item not added, resume to normal operation.");
        }
    }

    private static void handleMark(String line, Hashtable<String, Boolean> storedItems) {
        String item = line.substring(5);
        storedItems.put(item, true);
        System.out.println("I have marked the task as completed:");
        System.out.println("[X] " + item);
    }

    private static void addItem(String line, Hashtable<String, Boolean> storedItems) {
        storedItems.put(line, false);
        System.out.println("Added: " + line);
    }
}
