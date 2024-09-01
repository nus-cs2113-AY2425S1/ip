import java.util.Scanner;

public class Nell {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    /**
     * Prints out the formatted string for the task at a specified index
     *
     * @param task The task at the specified index
     * @param index The index of task
     */
    public static void printTaskAtIndex(Task task, int index) {
        System.out.println(String.format("   %d. %s", index, task));
    }

    public static void listAddTask(String taskToAdd) {
        // Stores text in task list
        tasks[taskCount] = new Task(taskToAdd);
        taskCount++;
    }

    /**
     * Adds a new ToDo task to the task list
     *
     * @param taskToAdd The task to be added to the list
     */
    public static void listAddToDo(String taskToAdd) {
        // Stores text in task list
        tasks[taskCount] = new ToDo(taskToAdd);
        taskCount++;
    }

    /**
     * Executes a todo command with a given command body
     *
     * @param commandBody The command body
     */
    public static void executeCommandToDo(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        listAddToDo(commandBody);
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    /**
     * Adds a new Deadline task to the task list
     *
     * @param taskToAdd The task to be added to the list
     */
    public static void listAddDeadline(String taskToAdd, String taskDoBy) {
        // Stores text in task list
        tasks[taskCount] = new Deadline(taskToAdd, taskDoBy);
        taskCount++;
    }

    /**
     * Executes a deadline command with a given command body
     *
     * @param commandBody The command body
     */
    public static void executeCommandDeadline(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        String[] commandWords = commandBody.split("/by");
        listAddDeadline(commandWords[0].trim(), commandWords[1].trim());
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    /**
     * Adds a new Deadline task to the task list
     *
     * @param taskToAdd The task to be added to the list
     */
    public static void listAddEvent(String taskToAdd, String eventStart, String eventEnd) {
        // Stores text in task list
        tasks[taskCount] = new Event(taskToAdd, eventStart, eventEnd);
        taskCount++;
    }

    /**
     * Executes an event command with a given command body
     *
     * @param commandBody The command body
     */
    public static void executeCommandEvent(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        String[] commandWords = commandBody.split("/from|/to", 3);
        listAddEvent(commandWords[0].trim(), commandWords[1].trim(), commandWords[2].trim());
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    public static void main(String[] args) {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        // Greets user
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            String[] commandWords = command.split(" ", 2);
            int taskIndex; // Stores index of task for mark and unmark commands
            switch (commandWords[0]) {
            case "bye":
                // Exits
                System.out.println("-> Bye. Hope to see you again soon!");
                isGettingCommands = false;
                break;

            case "list":
                // List out stored tasks
                System.out.println("-> The tasks listed are as follows:");
                for (int i = 0; i < taskCount; i++) {
                    // Prints all tasks in list
                    printTaskAtIndex(tasks[i], (i + 1));
                }
                break;

            case "mark":
                taskIndex = Integer.parseInt(commandWords[1]);
                if (taskIndex < 1 || taskIndex > taskCount) {
                    // Checks if entered value is valid
                    System.out.println("-> Invalid task!");
                } else {
                    // Marks a task as done
                    tasks[taskIndex - 1].setDone(true);
                    System.out.println("-> The following task has been marked done");
                    printTaskAtIndex(tasks[taskIndex - 1], taskIndex);
                }
                break;

            case "unmark":
                taskIndex = Integer.parseInt(commandWords[1]);
                if (taskIndex < 1 || taskIndex > taskCount) {
                    // Checks if entered value is valid
                    System.out.println("-> Invalid task!");
                } else {
                    // Marks a task as not done
                    tasks[taskIndex - 1].setDone(false);
                    System.out.println("-> The following task has been marked not done");
                    printTaskAtIndex(tasks[taskIndex - 1], taskIndex);
                }
                break;

            case "todo":
                executeCommandToDo(commandWords[1]);
                break;

            case "deadline":
                executeCommandDeadline(commandWords[1]);
                break;

            case "event":
                executeCommandEvent(commandWords[1]);
                break;

            default:
                listAddTask(command);
                System.out.printf("-> added: %s%n", command);
                break;
            }
        }
    }
}
