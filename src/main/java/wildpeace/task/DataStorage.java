package wildpeace.task;

import initializer.Initializer;
import initializer.LLMChat;
import wildpeace.exceptions.EmptyCommandException;
import wildpeace.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    private static ArrayList<Task> storedItems = new ArrayList<>();

    public static void storeData(Scanner scanner) throws EmptyCommandException {
        displayGuide();
        boolean exit = false;

        while (!exit) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                exit = true;
            } else {
                try {
                    processInput(line, scanner);
                } catch (EmptyCommandException e) {
                    System.out.println("Item cannot be empty.");
                } catch (InvalidInputException e) {
                    throw new RuntimeException(e);
                }
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

    private static void processInput(@org.jetbrains.annotations.NotNull String line, Scanner scanner)
    throws EmptyCommandException, InvalidInputException {
        if (line.equalsIgnoreCase("list")) {
            listItems();
        } else if (line.startsWith("todo ")) {
            if(line.substring(5).isEmpty()) {
                throw new EmptyCommandException();
            }
            addTask(new Task(line.substring(5), Task.TaskType.TODO));
        } else if (line.startsWith("deadline ")) {
            System.out.println("Enter deadline (by when):");
            String by = scanner.nextLine();
            if(line.substring(9).isEmpty()) {
                throw new EmptyCommandException();
            }
            addTask(new Task(line.substring(9), by, Task.TaskType.DEADLINE));
        } else if (line.startsWith("event ")) {
            System.out.println("Duration for the event: ");
            String at = scanner.nextLine();
            if(line.substring(6).isEmpty()) {
                throw new EmptyCommandException();
            }
            addTask(new Task(line.substring(6), at, Task.TaskType.EVENT));
        } else if (line.startsWith("mark ")) {
            try {
                int index = Integer.parseInt(line.substring(5).trim());
                markTask(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid mark number. Please enter a valid integer.");
            } catch (InvalidInputException e) {
                System.out.println("Invalid mark number");
            }
        } else if (line.startsWith("unmark ")) {
            try {
                int index = Integer.parseInt(line.substring(7).trim());
                unmarkTask(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid mark number. Please enter a valid integer.");
            } catch (InvalidInputException e) {
                System.out.println("Invalid unmark number");
            }

        } else if (line.equalsIgnoreCase("Q")) {
            displayGuide();
        } else
        {
            System.out.println("Unknown/ Incomplete command.");
        }
    }

    private static void addTask(Task task) {
        storedItems.add(task);
        System.out.println("Added: " + task);
    }

    private static void listItems() {
        for (Task task : storedItems) {
            int index = storedItems.indexOf(task);
            System.out.println(index + ": " + task);
        }
    }

    private static void markTask(Integer index) throws InvalidInputException {
        int key = index - 1;
        if(key >= storedItems.size() || key < 0) {
            throw new InvalidInputException();
        }
        Task task = storedItems.get(key);
        if (task != null) {
            task.mark();
            System.out.println("Marked as done: " + task);
        } else {
            System.out.println("Index " + String.valueOf(index) + " is not found.");
        }
    }

    private static void unmarkTask(Integer index ) throws InvalidInputException {
        int key = index - 1;
        if(key >= storedItems.size() || key < 0) {
            throw new InvalidInputException();
        }
        Task task = storedItems.get(key);
        if (task != null) {
            task.unmark();
            System.out.println("Unmarked: " + task);
        } else {
            System.out.println("Index " + String.valueOf(index) + " is not found.");
        }
    }
}
