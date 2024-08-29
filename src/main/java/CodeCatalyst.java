import java.util.Scanner;

public class CodeCatalyst {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
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
                handleMarkCommand(tasks, taskCount, input);
            } else if (input.startsWith("unmark ")) {
                handleUnmarkCommand(tasks, taskCount, input);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount += 1;
                System.out.println("         added: " + input);
            }
            printDivider();
        }
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
        System.out.println("         Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("         " + (i + 1) + ". " + tasks[i]);
        }
    }

    private static void handleMarkCommand(Task[] tasks, int taskCount, String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5));
            if (taskNumber >= 1 && (taskNumber - 1) < taskCount) {
                tasks[taskNumber - 1].markAsDone();
                System.out.println("         Nice! I've marked this task as done: ");
                System.out.println("         " + tasks[taskNumber - 1]);
            } else {
                System.out.println("         Invalid task number.");
            }
        } catch (NumberFormatException invalidNumberFormat) {
            System.out.println("         " + input.substring(5) + " is not a number");
        }
    }

    private static void handleUnmarkCommand(Task[] tasks, int taskCount, String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7));
            if (taskNumber >= 1 && (taskNumber - 1) < taskCount) {
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("         Ok, I've marked this task as not done yet: ");
                System.out.println("         " + tasks[taskNumber - 1] );
            } else {
                System.out.println("         Invalid task number.");
            }
        } catch (NumberFormatException invalidNumberFormat) {
            System.out.println("         " + input.substring(7) + " is not a number");
        }
    }
}
