import java.util.Scanner;

public class JerChatBot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        System.out.println("~~~~~~~~ Welcome to Jer Chat Bot ~~~~~~~~");
        System.out.println(" What you want?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (true) {
            String input = in.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Please tell me what to do next");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                continue;
            }

            String[] commands = input.split(" ", 2);

            if (commands[0].equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Finally done, k bye...");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else if (commands[0].equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" What you have to do:");
                if (numOfTasks == 0) {
                    System.out.println(" No tasks in your list currently.");
                } else {
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (commands[0].equals("mark")) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < numOfTasks) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(" Marked this task as done:");
                        System.out.println(" " + tasks[taskIndex]);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(" Invalid task number. Please try again.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" Invalid command or task number. Please try again.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            } else if (commands[0].equals("unmark")) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < numOfTasks) {
                        tasks[taskIndex].removeAsDone();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(" Marked this task as not done:");
                        System.out.println(" " + tasks[taskIndex]);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(" Invalid task number. Please try again.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" Invalid command or task number. Please try again.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            } else {
                // Add a new task, using the full input as the task description
                tasks[numOfTasks] = new Task(input);
                numOfTasks++;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("added: " + input);
                System.out.println(" And then what??");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        in.close();
    }
}
