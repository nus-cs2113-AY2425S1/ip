import java.util.Hashtable;
import java.util.Scanner;

public class DataStorage {
    private static Hashtable<String, Task> storedItems = new Hashtable<>();

    public static void storeData(Scanner scanner) {
        displayGuide();
        boolean exit = false;

        while (!exit) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                exit = true;
            } else {
                processInput(line, scanner);
            }
        }

        Initializer.initialise(scanner, new LLMChat());
    }

    private static void displayGuide() {
        System.out.println("List your plans in the following format:");
        System.out.println("todo/ deadline/ evet + task, eg. todo homework");
        System.out.println("You may also mark/ unmark existing task by: mark/ unmark + task");
        System.out.println("Return to tutorial by entering Q");
    }

    private static void processInput(String line, Scanner scanner) {
        if (line.equalsIgnoreCase("list")) {
            listItems();
        } else if (line.startsWith("todo ")) {
            addTask(new Task(line.substring(5), Task.TaskType.TODO));
        } else if (line.startsWith("deadline ")) {
            System.out.println("Enter deadline (by when):");
            String by = scanner.nextLine();
            addTask(new Task(line.substring(9), by, Task.TaskType.DEADLINE));
        } else if (line.startsWith("event ")) {
            System.out.println("Duration for the event: ");
            String at = scanner.nextLine();
            addTask(new Task(line.substring(6), at, Task.TaskType.EVENT));
        } else if (line.startsWith("mark ")) {
            markTask(line.substring(5));
        } else if (line.startsWith("unmark ")) {
            unmarkTask(line.substring(7));
        } else if (line.equalsIgnoreCase("Q")) {
            displayGuide();
        } else
        {
            System.out.println("Unknown command.");
        }
    }

    private static void addTask(Task task) {
        storedItems.put(task.getDescription(), task);
        System.out.println("Added: " + task);
    }

    private static void listItems() {
        for (Task task : storedItems.values()) {
            System.out.println(task);
        }
    }

    private static void markTask(String description) {
        Task task = storedItems.get(description);
        if (task != null) {
            task.mark();
            System.out.println("Marked as done: " + task);
        } else {
            System.out.println(description + " not found.");
        }
    }

    private static void unmarkTask(String description) {
        Task task = storedItems.get(description);
        if (task != null) {
            task.unmark();
            System.out.println("Unmarked: " + task);
        } else {
            System.out.println(description + " not found.");
        }
    }
}
