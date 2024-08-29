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

        Task[] tasks = new Task[100];
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
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + "." + tasks[i]);
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks[taskNumber]);
                } else {
                    System.out.println("    Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsNotDone();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + tasks[taskNumber]);
                } else {
                    System.out.println("    Invalid task number.");
                }
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("    added: " + input);
            }

            System.out.println(line);
        }
    }
}
