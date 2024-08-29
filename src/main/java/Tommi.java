import java.util.Scanner;

public class Tommi {
    private static final String[] tasks = new String[100];  // Array to store tasks
    private static int taskCount = 0;  // Counter to keep track of the number of tasks

    public static void main(String[] args) {
        printIntroMessage();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine(); // Read user input

            if (input.equals("bye")) { // Check if the user typed "bye"
                printExitMessage();
                break; // Exit the loop
            } else if (input.equals("list")) { // Handle the "list" command
                listTasks();
            } else { // Store the task and confirm it's added
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
        tasks[taskCount] = task;  // Store the task
        taskCount++;  // Increment the task counter
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
