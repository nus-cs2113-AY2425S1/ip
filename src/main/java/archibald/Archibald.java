package archibald;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import archibald.task.Deadline;
import archibald.task.Event;
import archibald.task.Task;
import archibald.task.Todo;

class ArchibaldException extends Exception {
    public ArchibaldException(String message) {
        super(message);
    }
}

public class Archibald {

    private static List<Task> tasks = new ArrayList<>();

    public static void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String input) {
        try {
            String[] parts = input.split(" ", 2);
            String type = parts[0].toLowerCase();
    
            if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ArchibaldException("Error: By royal decree, the description of a " + type + " cannot be empty.");
                }
            }
    
            Task newTask;
            switch (type) {
                case "todo":
                    newTask = new Todo(parts[1]);
                    break;
                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    if (deadlineParts.length < 2) {
                        throw new ArchibaldException("Error: Deadline must include '/by' followed by a date.");
                    }
                    newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                    break;
                case "event":
                    String[] eventParts = parts[1].split(" /from | /to ");
                    if (eventParts.length < 3) {
                        throw new ArchibaldException("Error: Event must include '/from' and '/to' followed by dates.");
                    }
                    newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    break;
                default:
                    throw new ArchibaldException("Error: In spite of my knowledge... I don't know what that means >:(");
            }
    
            tasks.add(newTask);
            printArchibaldResponse("Got it. I've added this task:\n  " + newTask +
                                   "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (ArchibaldException e) {
            printArchibaldResponse(e.getMessage());
        }
    }
    
    public static void printList() {
        if (tasks.isEmpty()) {
            printArchibaldResponse("The task list is currently empty.");
            return;
        }
        printArchibaldResponse("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static final String FILE_PATH = "./data/archibald.txt";

    // method to save tasks whenever the list change
    public static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            printArchibaldResponse("Error: Unable to save tasks to file.");
        }
    }

    // startup-er method to load tasks from the file
    public static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                Files.createDirectories(Paths.get("./data"));
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task;
                switch (type) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            printArchibaldResponse("Error: Unable to load tasks from file.");
        }
    }

    public static void deleteTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                printArchibaldResponse("Duly noted sire. Thy hath removed this task:\n  " + removedTask + "\nNow thou hath " + tasks.size() + " tasks in the list.");
            } else {
                throw new ArchibaldException("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Thouth hath given an invalid command format or task number.");
        }
    }

    public static void main(String[] args) {
        loadTasks(); // Load tasks previously saved on startup
        String name = "Archibald";
        printArchibaldResponse("Hello, I am known as " + name + ",\nhow may I be of assistance!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printArchibaldResponse("I bid thee farewell! May our paths cross again!");
                saveTasks(); //save task before exiting
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                markTask(input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addTask(input);
            }
            saveTasks(); //save tasks whenever list changes
        }

        scanner.close();
    }

    public static void markTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                printArchibaldResponse("Great Wizard of Skibidiness! Thou hath completed task:\n  " + tasks.get(taskIndex));
            } else {
                throw new ArchibaldException("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format or task number.");
        }
    }

    public static void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsNotDone();
                printArchibaldResponse("Big yikers! The task hath been marked undone:\n  " + tasks.get(taskIndex));
            } else {
                throw new ArchibaldException("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format or task number.");
        }
    }
}
