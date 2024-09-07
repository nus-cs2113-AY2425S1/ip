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
            try {
                processCommand(command, listing);
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
    }

    /**
     * Marks a task as done or not done based on the user command.
     *
     * @param words The split command words where the first word is "mark" or "unmark".
     * @param taskList The array of tasks where the task will be marked.
     * @throws CongloException.InvalidTaskNumber If the task number is invalid or out of range.
     */
    public static void markTask(String[] words, Task[] taskList) throws CongloException.InvalidTaskNumber {
        int i = Integer.parseInt(words[1].substring(0, 1)) - 1;
        if (i >= taskCount || i < 0) {
            throw new CongloException.InvalidTaskNumber();
        }
        if (words[0].equals("mark")) {
            taskList[i].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskList[i].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + taskList[i].getStatusIcon() + "] " + taskList[i].description );
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param sentence The command containing the task description.
     * @param taskList The array of tasks to which the task will be added.
     */
    public static void addTodo(String sentence, Task[] taskList) {
        Todo todo = new Todo(sentence);
        taskList[taskCount] = todo;
        taskCount++;
        echoTask(todo);
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param sentence The command containing the task description and deadline.
     * @param taskList The array of tasks to which the deadline will be added.
     * @throws CongloException.InvalidFormat If the deadline format is incorrect.
     */
    public static void addDeadline(String sentence, Task[] taskList) throws CongloException.InvalidFormat {
        if (!sentence.contains(" /by ")) {
            throw new CongloException.InvalidFormat("deadline");
        }
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
     * @throws CongloException.InvalidFormat If the event format is incorrect.
     */
    public static void addEvent(String sentence, Task[] taskList) throws CongloException.InvalidFormat {
        if (!sentence.contains(" /from ") || !sentence.contains(" /to ")) {
            throw new CongloException.InvalidFormat("event");
        }
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
     * @throws CongloException If the command is invalid or an error occurs while processing.
     */
    public static void processCommand(String command, Task[] taskList) throws CongloException {
        String[] words = command.split(" ", 2);
        switch(words[0]) {
        case "list":
            listTasks(taskList);
            break;
        case "unmark":
        case "mark":
            if (words.length == 1) {
                throw new CongloException.MissingTaskNumber(words[0]);
            }
            markTask(words, taskList);
            break;
        case "todo":
            if (words.length == 1) {
                throw new CongloException.MissingDescription("Todo");
            }
            addTodo(words[1], taskList);
            break;
        case "deadline":
            if (words.length == 1) {
                throw new CongloException.MissingDescription("deadline");
            }
            addDeadline(words[1], taskList);
            break;
        case "event":
            if (words.length == 1) {
                throw new CongloException.MissingDescription("event");
            }
            addEvent(words[1], taskList);
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
