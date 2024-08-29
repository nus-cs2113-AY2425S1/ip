import java.util.Scanner;

public class Tommi {
    private static final int MAX_TASKS = 100;
    private static final String[] tasks = new String[MAX_TASKS];  // Array to store tasks
    private static final boolean[] taskStatus = new boolean[MAX_TASKS];  // Array to store task completion status
    private static int taskCount = 0;  // Counter to keep track of the number of tasks

    public static void main(String[] args) {
        printIntroMessage();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); // Read user input

            if (input.equals("bye")) {
                printExitMessage();
                break; // Exit the loop
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                unmarkTask(taskIndex);
            } else {
                addTask(input);
            }
        }

        scanner.close(); // Close the scanner resource
    }

    private static void printIntroMessage() {
        String intro =
                          " ______                   \n"
                        + "/_  __/__  __ _  __ _  (_)\n"
                        + " / / / _ \\/  ' \\/  ' \\/ / \n"
                        + "/_/  \\___/_/_/_/_/_/_/_/  \n"
                        + "____________________________________________________________\n"
                        + "Hello! I'm Tommi!\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n";
        System.out.println(intro);
    }

    private static void addTask(String task) {
        tasks[taskCount] = task;
        taskStatus[taskCount] = false;  // By default, a new task is not done
        taskCount++;
        printLine();
        System.out.println("added: " + task);
        printLine();
    }

    private static void listTasks() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            String status = taskStatus[i] ? "[X]" : "[ ]";
            System.out.println((i + 1) + "." + status + " " + tasks[i]);
        }
        printLine();
    }

    private static void markTask(int index) {
        if (index >= 0 && index < taskCount) {
            taskStatus[index] = true;  // Mark the task as done
            printLine();
            System.out.println("Awesomesauce! I've marked this task as done:");
            System.out.println("  [X] " + tasks[index]);
            printLine();
        }
    }

    private static void unmarkTask(int index) {
        if (index >= 0 && index < taskCount) {
            taskStatus[index] = false;
            printLine();
            System.out.println("OK, I've marked this task as undone:");
            System.out.println("  [ ] " + tasks[index]);
            printLine();
        }
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
