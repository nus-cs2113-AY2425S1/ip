package conglo;

import conglo.manual.*;
import conglo.task.*;
import conglo.exception.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Conglo is a command-line task management application that allows users to
 * add tasks, mark tasks as done or not done, and list tasks. The tasks can
 * include ToDos, Events, and Deadlines.
 */
public class Conglo {

    // Constants
    protected static final String LINE_SEPARATOR = "-------------------------------------";

    // Class variables
    protected static String command;
    private static final ArrayList<Task> taskList = new ArrayList<>();
    protected static boolean isDelete = false;

    /**
     * The main method that starts the Conglo application. It handles user input
     * and processes commands until the user types "bye".
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        greetUser();
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();
        while (!command.equals("bye")) {
            try {
                processCommand(command);
            } catch (CongloException e) {
                System.out.println(e.getMessage());
            }
            printLineSeparator();
            command = scanner.nextLine();
        }
        scanner.close();
        sayGoodbye();
    }

    /**
     * Prints a line separator to the console.
     */
    public static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Greets the user when the application starts.
     */
    public static void greetUser() {
        printLineSeparator();
        System.out.println("Hola! I'm Conglo, the friendly task manager.");
        printLineSeparator();
        QuickManual.printManual();
        printLineSeparator();
    }

    /**
     * Echoes the details of the newly added task and the current task count.
     *
     * @param task The task to be echoed.
     */
    public static void echoTask(Task task) {
        int size = taskList.size();
        if (isDelete) {
            System.out.println("Okie! Task is removed from list:");
            size--;
        } else {
            System.out.println("All done! Task added to list:");
        }
        String taskSuffix = size > 2 ? " tasks" : " task";
        System.out.println(" " + task.toString());
        System.out.println("The list has " + size + taskSuffix + " now.");
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    public static void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("The list is empty!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    /**
     * Marks a task as done or not done based on the user command.
     *
     * @param words The split command words where the first word is "mark" or "unmark".
     * @throws CongloException.InvalidTaskNumber If the task number is invalid or out of range.
     */
    public static void markTask(String[] words) throws CongloException.InvalidTaskNumber {
        int i;
        try {
            i = Integer.parseInt(words[1].substring(0, 1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Please provide a task number >.<");
            return;
        }

        if (i >= taskList.size() || i < 0) {
            throw new CongloException.InvalidTaskNumber();
        }
        if (words[0].equals("mark")) {
            taskList.get(i).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskList.get(i).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskList.get(i).toString());
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param sentence The command containing the task description.
     */
    public static void addTodo(String sentence) {
        Todo todo = new Todo(sentence);
        taskList.add(todo);
        echoTask(todo);
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param sentence The command containing the task description and deadline.
     * @throws CongloException.InvalidFormat If the deadline format is incorrect.
     */
    public static void addDeadline(String sentence) throws CongloException.InvalidFormat {
        if (!sentence.contains(" /by ")) {
            throw new CongloException.InvalidFormat("deadline");
        }
        String[] words = sentence.split(" /by ");
        Deadline deadline = new Deadline(words[0], words[1]);
        taskList.add(deadline);
        echoTask(deadline);
    }

    /**
     * Adds an event task to the task list.
     *
     * @param sentence The command containing the task description, start, and end times.
     * @throws CongloException.InvalidFormat If the event format is incorrect.
     */
    public static void addEvent(String sentence) throws CongloException.InvalidFormat {
        if (!sentence.contains(" /from ") || !sentence.contains(" /to ")) {
            throw new CongloException.InvalidFormat("event");
        }
        String[] words = sentence.split(" /from | /to ");
        Event event = new Event(words[0], words[1], words[2]);
        taskList.add(event);
        echoTask(event);
    }

    public static void deleteTask(String word) throws CongloException.InvalidTaskNumber {
        int i;
        try {
            i = Integer.parseInt(word) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Please provide a task number >.<");
            return;
        }
        if (i >= taskList.size() || i < 0) {
            throw new CongloException.InvalidTaskNumber();
        }
        isDelete = true;
        echoTask(taskList.get(i));
        taskList.remove(i);
        isDelete = false;
    }

    /**
     * Processes the user's command and invokes the corresponding action.
     *
     * @param command The full command entered by the user.
     * @throws CongloException If the command is invalid or an error occurs while processing.
     */
    public static void processCommand(String command) throws CongloException {
        String[] words = command.split(" ", 2);
        switch(words[0]) {
        case "list":
            listTasks();
            break;
        case "unmark":
        case "mark":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new CongloException.MissingTaskNumber(words[0]);
            }
            markTask(words);
            break;
        case "delete":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new CongloException.MissingTaskNumber(words[0]);
            }
            deleteTask(words[1]);
            break;
        case "todo":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new CongloException.MissingDescription("Todo");
            }
            addTodo(words[1]);
            break;
        case "deadline":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new CongloException.MissingDescription("deadline");
            }
            addDeadline(words[1]);
            break;
        case "event":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new CongloException.MissingDescription("event");
            }
            addEvent(words[1]);
            break;
        default:
            throw new CongloException.UnknownCommand();
        }
    }

    /**
     * Prints a goodbye message when the user exits the application.
     */
    public static void sayGoodbye() {
        System.out.println("Goodbye. See you next time!");
        printLineSeparator();
    }
}
