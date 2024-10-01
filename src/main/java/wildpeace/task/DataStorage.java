package wildpeace.task;

import initializer.Initializer;
import wildpeace.exceptions.EmptyCommandException;
import wildpeace.exceptions.InvalidInputException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    private static ArrayList<Task> storedItems = new ArrayList<>();
    private static final String FILE_PATH = "tasks.json";
    private static Gson gson = new Gson();
    private static void saveToJson() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(storedItems, writer);  // Save the current state of storedItems to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromJson() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();  // Correct Type for Task list
            ArrayList<Task> loadedTasks = gson.fromJson(reader, listType);  // Read and load tasks from the file

            if (loadedTasks != null) {
                storedItems = loadedTasks;  // If the file has valid tasks, assign it to storedItems
            }

        } catch (IOException e) {
            // If the file doesn't exist or can't be read, start with an empty list
            System.out.println("No existing tasks found. Starting fresh.");
        }
    }
    public static void storeData(Scanner scanner) throws EmptyCommandException {
        loadFromJson();
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

        Initializer.initialise(scanner);
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
        } else if (line.startsWith("delete")) {
            try {
                int index = Integer.parseInt(line.substring(7).trim());
                delete(index);
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
        saveToJson();
    }

    private static void addTask(Task task) {
        storedItems.add(task);
        System.out.println("Added: " + task);
        saveToJson();
    }

    private static void listItems() {
        for (Task task : storedItems) {
            int index = storedItems.indexOf(task);
            System.out.println(index + 1 + ": " + task);
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
        saveToJson();
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
        saveToJson();
    }
    private static void delete(Integer index) throws InvalidInputException {
        int key = index - 1;
        if(key >= storedItems.size() || key < 0) {
            throw new InvalidInputException();
        }
        System.out.println("Noted, I have removed this task:" );
        System.out.println(storedItems.get(key));
        storedItems.remove(key);
        System.out.println("Now you have " + storedItems.size() + " items in your plan" );
    }
}
