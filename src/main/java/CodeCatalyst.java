import java.util.Scanner;

public class CodeCatalyst {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        printGreeting();

        while (true) {
            String input = scanner.nextLine();
            printDivider();

            if (input.equals("bye")) {
                printGoodbye();
                break;
            } else if (input.equals("list")) {
                printTaskList(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                handleTaskStatusChange(tasks, taskCount, input, true);
            } else if (input.startsWith("unmark ")) {
                handleTaskStatusChange(tasks, taskCount, input, false);
            } else if (input.startsWith("todo ")) {
                taskCount = addTask(tasks, taskCount, new Todo(input.substring(5)));
            } else if (input.startsWith("deadline ")) {
                taskCount = addDeadlineTask(tasks, taskCount, input);
            } else if (input.startsWith("event ")) {
                taskCount = addEventTask(tasks, taskCount, input);
            } else {
                System.out.println("Invalid input! Please enter a valid command.");
            }
            printDivider();
        }
        scanner.close();
    }


    private static void printDivider() {
        System.out.println("        ________________________________________________________\n");
    }

    private static void printGreeting() {
        printDivider();
        System.out.println("         Hello, I'm CodeCatalyst.");
        System.out.println("         What can I do for you?");
        printDivider();
    }

    private static void printGoodbye() {
        System.out.println("         Bye. Hope to see you again soon!");
        printDivider();
    }

    private static void printTaskList(Task[] tasks, int taskCount) {
        System.out.println("         Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("         " + (i + 1) + ". " + tasks[i]);
        }
    }

    private static void handleTaskStatusChange(Task[] tasks, int taskCount, String input, boolean isMark) {
        int taskNumber = extractTaskNumber(input, isMark);
        if (taskNumber == -1) {
            return;  // Invalid task number, already handled in extractTaskNumber.
        }
        changeTaskStatus(tasks, taskCount, taskNumber, isMark);
    }

    /**
     * Extracts the task number from the input command.
     *
     * @param input The user input command.
     * @param isMark True if the command is to mark a task, false for unmark.
     * @return The valid task number, or -1 if invalid.
     */
    private static int extractTaskNumber(String input, boolean isMark) {
        int prefixLength = isMark ? 5 : 7; // length of "mark " or "unmark "
        try {
            return Integer.parseInt(input.substring(prefixLength));
        } catch (NumberFormatException invalidNumberFormat) {
            System.out.println("         " + input.substring(prefixLength) + " is not a number");
            return -1;
        }
    }

    /**
     * Change the status of the task to done or not done.
     *
     * @param tasks The array of tasks.
     * @param taskCount The current number of tasks.
     * @param taskNumber The task number to change status.
     * @param isMark True to mark the task as done, false to unmark.
     */
    private static void changeTaskStatus(Task[] tasks, int taskCount, int taskNumber, boolean isMark) {
        int taskIndex = taskNumber - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            if (isMark) {
                tasks[taskIndex].markAsDone();
                System.out.println("         Nice! I've marked this task as done:");
            } else {
                tasks[taskIndex].markAsNotDone();
                System.out.println("         Ok, I've marked this task as not done yet:");
            }
            System.out.println("         " + tasks[taskIndex]);
        } else {
            System.out.println("         Invalid task number.");
        }
    }

    private static int addTask(Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        System.out.println("         Got it. I've added this task:");
        System.out.println("         " + tasks[taskCount]);
        System.out.println("         Now you have " + (taskCount + 1) + " tasks in the list.");
        return taskCount + 1;
    }

    private static int addDeadlineTask(Task[] tasks, int taskCount, String input) {
        String[] parts = input.substring(9).split(" /by ");
        return addTask(tasks, taskCount, new Deadline(parts[0], parts[1]));
    }

    private static int addEventTask(Task[] tasks, int taskCount, String input) {
        String[] parts = input.substring(6).split(" /from | /to ");
        return addTask(tasks, taskCount,  new Event(parts[0], parts[1], parts[2]));
    }
}
