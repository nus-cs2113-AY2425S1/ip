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
    private static void printTaskAtIndex(Task task, int index) {
        System.out.println(String.format("   %d. %s", index, task));
    }

    /**
     * Given a task number, checks if the numbered task is valid (in the task list)
     *
     * @param taskNumber the task number
     * @return true if taskIndex is within range, false otherwise
     */
    private static boolean checkIfTaskValid(int taskNumber) {
        if (taskNumber < 1) {
            return false;
        } else if (taskNumber > taskCount) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds a new task to the task list
     *
     * @param taskToAdd The task to be added to the list
     */
    private static void listAddTask(Task taskToAdd) {
        tasks[taskCount] = taskToAdd;
        taskCount++;
    }

    /**
     * Lists out the currently stored tasks in TaskList
     *
     */
    private static void listTasks() {
        System.out.println("-> The tasks listed are as follows:");
        for (int i = 0; i < taskCount; i++) {
            // Prints all tasks in list
            printTaskAtIndex(tasks[i], (i + 1));
        }
    }

    /**
     * Adds a ToDo to the task list
     *
     * @param commandBody The command body
     */
    private static void addToDo(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        ToDo toDoToAdd = new ToDo(commandBody);
        listAddTask(toDoToAdd);
        System.out.println("   " + toDoToAdd);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    /**
     * Adds a deadline to the task list
     *
     * @param commandBody The command body
     */
    private static void addDeadline(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        String[] commandWords = commandBody.split("/by");
        Deadline deadlineToAdd = new Deadline(commandWords[0].trim(), commandWords[1].trim());
        listAddTask(deadlineToAdd);
        System.out.println("   " + deadlineToAdd);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    /**
     * Adds an event command to the task list
     *
     * @param commandBody The command body
     */
    private static void addEvent(String commandBody) {
        System.out.println("-> The task has been added to the list:");
        String[] commandWords = commandBody.split("/from|/to", 3);
        Event eventToAdd = new Event(commandWords[0].trim(), commandWords[1].trim(), commandWords[2].trim());
        listAddTask(eventToAdd);
        System.out.println("   " + eventToAdd);
        System.out.println(String.format("   The list now has %d tasks", taskCount));
    }

    /**
     * Unmarks a task as done
     *
     * @param commandBody The command body
     */
    private static void unmarkTask(String commandBody) {
        int taskIndex = Integer.parseInt(commandBody);
        if (checkIfTaskValid(taskIndex)) {
            tasks[taskIndex - 1].setDone(false);
            System.out.println("-> The following task has been marked not done:");
            printTaskAtIndex(tasks[taskIndex - 1], taskIndex);
        } else {
            System.out.println("-> Invalid task!");
        }
    }

    /**
     * Marks a task as done
     *
     * @param commandBody The command body
     */
    private static void markTask(String commandBody) {
        int taskIndex = Integer.parseInt(commandBody);
        if (checkIfTaskValid(taskIndex)) {
            tasks[taskIndex - 1].setDone(true);
            System.out.println("-> The following task has been marked done:");
            printTaskAtIndex(tasks[taskIndex - 1], taskIndex);
        } else {
            System.out.println("-> Invalid task!");
        }
    }

    /**
     * Executes an add task command (no command word) with a given command body
     *
     * @param command The command body
     */
    private static void addTask(String command) {
        Task taskToAdd = new Task(command);
        listAddTask(taskToAdd);
        System.out.printf("-> added: %s%n", command);
    }

    /**
     * Executes a bye command
     *
     */
    private static void executeCommandBye() {
        System.out.println("-> Bye. Hope to see you again soon!");
    }

    /**
     * Greet the user upon program startup
     *
     */
    private static void greetUser() {
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");
    }

    /**
     * Get commands from user and execute commands received
     *
     */
    private static void getCommands() {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            String[] commandWords = command.split(" ", 2);
            switch (commandWords[0]) {
            case "bye":
                executeCommandBye();
                isGettingCommands = false;
                break;

            case "list":
                listTasks();
                break;

            case "mark":
                markTask(commandWords[1]);
                break;

            case "unmark":
                unmarkTask(commandWords[1]);
                break;

            case "todo":
                addToDo(commandWords[1]);
                break;

            case "deadline":
                addDeadline(commandWords[1]);
                break;

            case "event":
                addEvent(commandWords[1]);
                break;

            default:
                addTask(command);
                break;
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        getCommands();
    }
}