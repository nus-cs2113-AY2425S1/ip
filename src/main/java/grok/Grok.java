package grok;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Grok {
    private static final String FILE_PATH = "./data/grok.txt";
    private static final Task[] tasks = new Task[100];  // Made 'final'
    private static int taskCount = 0;

    public static void main(String[] args) {
        if (!loadTasksFromFile()) {
            System.out.println("Starting with an empty task list.");
        }

        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();
        printLine();

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    printLine();
                    System.out.println("Bye. Hope to see you again soon! Keep Grokking :)");
                    printLine();
                    saveTasksToFile();
                    break;
                } else if (input.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    printLine();
                } else if (input.startsWith("mark")) {
                    int taskNumber = getTaskNumber(input, "mark");
                    tasks[taskNumber].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("unmark")) {
                    int taskNumber = getTaskNumber(input, "unmark");
                    tasks[taskNumber].markAsNotDone();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new GrokException("Oh no, todo cannot be empty. Do something!");
                    }
                    tasks[taskCount] = new Todo(description);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("deadline")) {
                    String[] details = input.substring(8).trim().split(" /by ");
                    if (details.length < 2) {
                        throw new GrokException("Invalid deadline command. Please use: deadline <description> /by <date/time>");
                    }
                    tasks[taskCount] = new Deadline(details[0], details[1]);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("event")) {
                    String[] details = input.substring(5).trim().split(" /from | /to ");
                    if (details.length < 3) {
                        throw new GrokException("Invalid event command. Please use: event <description> /from <start> /to <end>");
                    }
                    tasks[taskCount] = new Event(details[0], details[1], details[2]);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("delete")) {
                    int taskNumber = getTaskNumber(input, "delete");
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new GrokException("Invalid task number. Please enter a number within the range.");
                    }
                    printLine();
                    System.out.println("Okay, I've removed this task:");
                    System.out.println(tasks[taskNumber]);
                    // Shift tasks to fill the gap
                    for (int i = taskNumber; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    taskCount--;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else {
                    throw new GrokException("I'm sorry, I don't grok that command. Please try again :(");
                }
            } catch (GrokException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("Invalid task number command. Please enter a valid number.");
                printLine();
            }
        }

        scanner.close();
    }

    /**
     * Extracts and returns the task number from the user input for commands
     * such as "mark", "unmark", or "delete".
     *
     * @param input   The user's input string containing the command and task number.
     * @param command The command to extract the task number from (e.g., "mark", "unmark", "delete").
     * @return The task number (zero-based index) from the input.
     * @throws GrokException If the task number is not provided or is invalid.
     */
    private static int getTaskNumber(String input, String command) throws GrokException {
        String taskNumberStr = input.substring(command.length()).trim();
        if (taskNumberStr.isEmpty()) {
            throw new GrokException(command + " must be followed by a task number.");
        }
        return Integer.parseInt(taskNumberStr) - 1;
    }

    /**
     * Prints the welcome message when the program starts. It provides a description of
     * the available commands that the user can type.
     */
    private static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello, I am Grok! Your favourite personal assistant that helps you keep track of tasks :)");
        System.out.println("Here are the list of things Grok can do for you:");
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark and the task number to indicate completion of task");
        System.out.println("5. Type delete followed by the task number to remove a task from your list");
        System.out.println("6. Type list to view your list of tasks.");
        System.out.println("7. Type bye to exit the programme");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Saves the tasks to the file at the specified file path.
     * If the directory doesn't exist, it creates it. This method handles
     * file creation, writing, and error handling related to file I/O.
     */
    private static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists() && !directory.mkdir()) {
                System.out.println("Failed to create directory.");
                return;
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskCount; i++) {
                fileWriter.write(tasks[i].toSaveFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads tasks from the file at the specified file path.
     * It reads each task line by line, processes the task type,
     * and populates the task array with the appropriate task objects.
     *
     * @return true if tasks were loaded successfully, false otherwise.
     */
    private static boolean loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Task file not found. Generating new task file...");
                return false;  // No file exists, returning false
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                String[] taskDetails = taskLine.split(" \\| ");
                String taskType = taskDetails[0];
                boolean isDone = taskDetails[1].equals("1");

                switch (taskType) {
                case "T":
                    tasks[taskCount] = new Todo(taskDetails[2]);
                    break;
                case "D":
                    tasks[taskCount] = new Deadline(taskDetails[2], taskDetails[3]);
                    break;
                case "E":
                    tasks[taskCount] = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                    break;
                default:
                    System.out.println("Unknown task type encountered: " + taskType);
                    continue;
                }

                if (isDone) {
                    tasks[taskCount].markAsDone();
                }
                taskCount++;
            }
            fileScanner.close();
            return true;  // Return true if tasks were loaded successfully
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks. Generating a new task file...");
            return false;  // Return false if loading fails
        }
    }
}
