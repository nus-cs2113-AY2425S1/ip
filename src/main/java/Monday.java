import java.util.Scanner;

public class Monday {
    public static void main(String[] args) {
        String logo = " __  __                 _             \n"
                + "|  \\/  | ___  _ __   __| | __ _ _   _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |/ _` | | | |\n"
                + "| |  | | (_) | | | | (_| | (_| | |_| |\n"
                + "|_|  |_|\\___/|_| |_|\\__,_|\\__,_|\\__, |\n"
                + "                                |___/  \n";
        String line = "____________________________________________________________";

        // Array to store tasks
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(line);
        System.out.println("    Hello! I'm MONDAY\n    What can I do for you?");
        System.out.println(line);

        while (true) {
            input = scanner.nextLine();
            System.out.println(line);

            if (input.equalsIgnoreCase("list")) {
                // Display the list of tasks
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                // Add the task to the list
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("    added: " + input);
            }

            System.out.println(line);
        }
    }
}
