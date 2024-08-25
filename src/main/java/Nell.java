import java.util.Scanner;

public class Nell {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
            String[] commandWords = command.split(" ");
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
                    // Checks if tasked is marked done
                    char taskStatus = tasks[i].getStatusIcon();
                    System.out.printf("   %d. [%c] %s", (i + 1), taskStatus,
                            tasks[i].getDescription() + System.lineSeparator());
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
                    System.out.printf("   %d. [X] %s", taskIndex,
                            tasks[taskIndex - 1].getDescription() + System.lineSeparator());
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
                    System.out.printf("   %d. [ ] %s", taskIndex,
                            tasks[taskIndex - 1].getDescription() + System.lineSeparator());
                }
                break;

            default:
                // Stores text in task list
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.printf("-> added: %s%n", command);
                break;
            }
        }
    }
}
