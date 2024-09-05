import java.util.Scanner;

public class Bob {

    public static final String SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = "  ____        _\n"
                + " | |_) \\ ___ | |___\n"
                + " |  _ //  _  \\   _ \\\n"
                + " | |_)\\\\ (_) /  |_) |\n"
                + " |____/ \\___/|_|___/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(SEPARATOR);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
                break;
            } else if (input.equals("list")) {
                System.out.println(SEPARATOR);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + "." + tasks[i]);
                }
                System.out.println(SEPARATOR);
            } else if (input.startsWith("mark ")) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println(SEPARATOR);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println(SEPARATOR);
            } else if (input.startsWith("unmark ")) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].unmark();
                System.out.println(SEPARATOR);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println(SEPARATOR);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                printString(tasks, taskCount);
            } else if (input.startsWith("deadline ")) {
                String[] components = input.split(" /by ");
                String description = components[0].substring(9);
                String by = components[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                printString(tasks, taskCount);
            } else if (input.startsWith("event ")) {
                String description = input.substring(6, input.indexOf(" /from "));
                String from = input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
                String to = input.substring(input.indexOf(" /to ") + 5);
                tasks[taskCount] = new Event(description,from, to);
                taskCount++;
                printString(tasks, taskCount);
            } else {
                System.out.println(SEPARATOR);
                System.out.println("Sorry I don't understand what you mean");
                System.out.println(SEPARATOR);
            }
        }
        scanner.close();
    }

    public static void printString(Task[] tasks, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
    }
}