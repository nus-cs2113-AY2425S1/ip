import java.util.Scanner;

/**
 * Conglo is a command-line task management application that allows users to
 * add tasks, mark tasks as done or not done, and list tasks. The tasks can
 * include ToDos, Events, and Deadlines.
 */
public class Conglo {

    // Constants
    protected static final int MAX_TASKS = 100;
    protected static final String LINE_SEPARATOR = "-------------------------------------";

    // Class variables
    protected static String command;
    protected static int taskCount = 0;

    /**
     * The main method that starts the Conglo application. It handles user input
     * and processes commands until the user types "bye".
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        greetUser();
        Scanner scanner = new Scanner(System.in);
        Task[] listing = new Task[MAX_TASKS];
        command = scanner.nextLine();
        while (!command.equals("bye")) {
            processCommand(command, listing);
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
        System.out.println("Hola! I'm Conglo the friendly bot.");
        System.out.println("What brings you here today?");
        printLineSeparator();
    }

    /**
     * Echoes the details of the newly added task and the current task count.
     *
     * @param task The task to be echoed.
     */
    public static void echoTask(Task task) {
        String taskSuffix = taskCount > 1 ? " tasks" : " task";
        System.out.println("All done! Task added to list:");
        System.out.println(" " + task.toString());
        System.out.println("The list has " + taskCount + taskSuffix + " now.");
        printLineSeparator();
    }

    /**
     * Lists all the tasks currently in the task list.
     *
     * @param taskList The array of tasks to list.
     */
    public static void listTasks(Task[] taskList) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
        printLineSeparator();
    }

    /**
     * Marks a task as done or not done based on the user command.
     *
     * @param words The split command words where the first word is "mark" or "unmark".
     * @param taskList The array of tasks where the task will be marked.
     */
    public static void markTask(String[] words, Task[] taskList) {
        int i = Integer.parseInt(words[1].substring(0, 1)) - 1;
        if (i >= taskCount || i < 0) {
            System.out.println("Invalid task number");
            return;
        }
        if (words[0].equals("mark")) {
            taskList[i].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskList[i].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + taskList[i].getStatusIcon() + "] " + taskList[i].description );
        printLineSeparator();
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param sentence The command containing the task description and deadline.
     * @param taskList The array of tasks to which the deadline will be added.
     */
    public static void addDeadline(String sentence, Task[] taskList) {
        String[] words = sentence.split(" /by ");
        Deadline deadline = new Deadline(words[0], words[1]);
        taskList[taskCount] = deadline;
        taskCount++;
        echoTask(deadline);
    }

    /**
     * Adds an event task to the task list.
     *
     * @param sentence The command containing the task description, start, and end times.
     * @param taskList The array of tasks to which the event will be added.
     */
    public static void addEvent(String sentence, Task[] taskList) {
        String[] words = sentence.split(" /from | /to ");
        Event event = new Event(words[0], words[1], words[2]);
        taskList[taskCount] = event;
        taskCount++;
        echoTask(event);
    }

    /**
     * Processes the user's command and invokes the corresponding action.
     *
     * @param command The full command entered by the user.
     * @param taskList The array of tasks to operate on.
     */
    public static void processCommand(String command, Task[] taskList) {
        String[] words = command.split(" ", 2);
        switch(words[0]) {
        case "list":
            listTasks(taskList);
            break;
        case "unmark":
        case "mark":
            markTask(words, taskList);
            break;
        case "todo":
            Todo todo = new Todo(words[1]);
            taskList[taskCount] = todo;
            taskCount++;
            echoTask(todo);
            break;
        case "deadline":
            addDeadline(words[1], taskList);
            break;
        case "event":
            addEvent(words[1], taskList);
            break;
        default:
            System.out.println("Please provide the task type.");
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
