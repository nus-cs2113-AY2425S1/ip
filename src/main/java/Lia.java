import task.Task;
import task.Event;
import task.ToDo;
import task.Deadline;
import exception.LiaException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Lia is a simple chatbot program that helps users manage tasks.
 */
public class Lia {
    private static final String INDENTATION = "    "; // Constant for Chatbot output indentation
    private static final String FILE_PATH = "./data/lia.txt"; // Path to save tasks

    public static void main(String[] args) {
        // Customizing the chatbot with the name Lia
        String LOGO =
                """
                             ██▓     ██▓ ▄▄▄     \s
                            ▓██▒    ▓██▒▒████▄   \s
                            ▒██░    ▒██▒▒██  ▀█▄ \s
                            ▒██░    ░██░░██▄▄▄▄██\s
                            ░██████▒░██░ ▓█   ▓██▒
                            ░ ▒░▓  ░░▓   ▒▒   ▓▒█░
                            ░ ░ ▒  ░ ▒ ░  ▒   ▒▒ ░
                            ░ ░    ▒ ░  ░   ▒  \s
                                ░  ░ ░        ░  ░
                        """;

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = loadTasks();  // Load tasks from file

        // Greet the user with enthusiasm
        printLine();
        System.out.println(INDENTATION + "Hello! I'm \n" + LOGO);
        System.out.println(INDENTATION + "What can I do for you?");
        printLine();

        // Chatbot loop: keep asking for input until "bye" is entered
        while (true) {
            try {
                input = scanner.nextLine().trim();
                String[] inputArr = input.split(" ", 2);  // Splitting command and details

                // If the user types "bye", end the loop with a warm farewell
                if (inputArr[0].equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                    printLine();
                    break;
                }

                // Handle commands
                handleCommand(inputArr, tasks);

                // Save tasks to file after every command
                saveTasks(tasks);

            } catch (LiaException e) {
                // Handle any Lia-specific exceptions
                printLine();
                System.out.println(INDENTATION + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    /**
     * Loads tasks from the file at startup.
     * <p>
     * If the file contains invalid or corrupted data (i.e., not in the expected format),
     * the line will be skipped, and a warning will be printed.
     *
     * @return The list of tasks loaded from the file.
     */
    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get(FILE_PATH);

        try {
            if (!Files.exists(filePath)) {
                // If file doesn't exist, create the file and the directory
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } else {
                // Read the file line by line and load tasks
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    try {
                        // Validate and parse the task line
                        String[] data = line.split(" \\| ");
                        switch (data[0]) {
                        case "T":
                            if (data.length != 3) throw new IOException("Invalid ToDo format.");
                            ToDo todo = new ToDo(data[2]);
                            if (data[1].equals("1")) todo.markAsDone();
                            tasks.add(todo);
                            break;
                        case "D":
                            if (data.length != 4) throw new IOException("Invalid Deadline format.");
                            Deadline deadline = new Deadline(data[2], data[3]);
                            if (data[1].equals("1")) deadline.markAsDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            if (data.length != 5) throw new IOException("Invalid Event format.");
                            Event event = new Event(data[2], data[3], data[4]);
                            if (data[1].equals("1")) event.markAsDone();
                            tasks.add(event);
                            break;
                        default:
                            System.out.println(INDENTATION + "Warning: Unrecognized task type in file. Skipping line.");
                        }
                    } catch (IOException | ArrayIndexOutOfBoundsException e) {
                        // Catch and handle malformed or corrupted lines
                        System.out.println(INDENTATION + "Warning: Corrupted data in file. Skipping line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Handles the user input command and performs the corresponding action.
     *
     * @param inputArr The split user input containing the command and arguments.
     * @param tasks The list of tasks to manage.
     * @throws LiaException if the input is invalid or unrecognized.
     */
    private static void handleCommand(String[] inputArr, ArrayList<Task> tasks) throws LiaException {
        String command = inputArr[0];

        switch (command.toLowerCase()) {
        case "list":
            printTasks(tasks);
            break;
        case "todo":
            if (inputArr.length < 2 || inputArr[1].isBlank()) {
                throw new LiaException("Oops! The description of a todo cannot be empty.");
            }
            tasks.add(new ToDo(inputArr[1]));
            addTaskAndPrint(tasks.get(tasks.size() - 1), tasks.size());
            break;
        case "deadline":
            if (inputArr.length < 2 || !inputArr[1].contains("/by")) {
                throw new LiaException("Oops! The deadline command requires a description and '/by' followed by a date.");
            }
            String[] deadlineDetails = inputArr[1].split(" /by ", 2);
            tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            addTaskAndPrint(tasks.get(tasks.size() - 1), tasks.size());
            break;
        case "event":
            if (inputArr.length < 2 || !inputArr[1].contains("/from") || !inputArr[1].contains("/to")) {
                throw new LiaException("Oops! The event command requires a description, '/from', and '/to' followed by times.");
            }
            String[] eventDetails = inputArr[1].split(" /from ", 2);
            String[] times = eventDetails[1].split(" /to ", 2);
            tasks.add(new Event(eventDetails[0], times[0], times[1]));
            addTaskAndPrint(tasks.get(tasks.size() - 1), tasks.size());
            break;
        case "mark":
            if (inputArr.length < 2) {
                throw new LiaException("Oops! You must specify a task number to mark.");
            }
            markTask(inputArr[1], tasks, true);
            break;
        case "unmark":
            if (inputArr.length < 2) {
                throw new LiaException("Oops! You must specify a task number to unmark.");
            }
            markTask(inputArr[1], tasks, false);
            break;
        case "delete":
            if (inputArr.length < 2) {
                throw new LiaException("Oops! You must specify a task number to delete.");
            }
            deleteTask(inputArr[1], tasks);
            break;
        default:
            throw new LiaException("Oops! I don't recognize that command.");
        }
    }

    /**
     * Marks or unmarks a task as done or not done.
     *
     * @param taskNumberStr The task number to mark or unmark.
     * @param tasks The list of tasks.
     * @param markDone True to mark the task as done, false to unmark it.
     * @throws LiaException if the task number is invalid.
     */
    private static void markTask(String taskNumberStr, ArrayList<Task> tasks, boolean markDone) throws LiaException {
        try {
            int taskIndex = Integer.parseInt(taskNumberStr) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new LiaException("Oops! Task number " + taskNumberStr + " does not exist.");
            }

            Task task = tasks.get(taskIndex);

            if (markDone) {
                task.markAsDone();
                printLine();
                System.out.println(INDENTATION + "Nice! I've marked this task as done:");
            } else {
                task.markAsNotDone();
                printLine();
                System.out.println(INDENTATION + "OK, I've unmarked this task:");
            }

            System.out.println(INDENTATION + task);
            printLine();

        } catch (NumberFormatException e) {
            throw new LiaException("Oops! Please enter a valid task number.");
        }
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    private static void printTasks(ArrayList<Task> tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println(INDENTATION + "No tasks found.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        printLine();
    }

    /**
     * Adds a task and prints the confirmation message.
     *
     * @param task The task to be added.
     * @param taskCount The current number of tasks.
     */
    private static void addTaskAndPrint(Task task, int taskCount) {
        printLine();
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumberStr The task number to delete.
     * @param tasks The list of tasks.
     * @throws LiaException if the task number is invalid.
     */
    private static void deleteTask(String taskNumberStr, ArrayList<Task> tasks) throws LiaException {
        try {
            int taskIndex = Integer.parseInt(taskNumberStr) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new LiaException("Oops! Task number " + taskNumberStr + " does not exist.");
            }

            Task task = tasks.remove(taskIndex); // Remove the task from the list
            printLine();
            System.out.println(INDENTATION + "Noted. I've removed this task:");
            System.out.println(INDENTATION + task.toString());
            System.out.println(INDENTATION + "Now you have " + tasks.size() + " tasks in the list.");
            printLine();

        } catch (NumberFormatException e) {
            throw new LiaException("Oops! Please enter a valid task number.");
        }
    }

    /**
     * Saves the current tasks to a file.
     *
     * @param tasks The list of tasks to save.
     */
    private static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(INDENTATION + "Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println(INDENTATION + "___________________________________________________________");
    }
}

