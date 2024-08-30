import java.util.Scanner;

public class Monday {
    private static final String LOGO = " __  __                 _             \n"
            + "|  \\/  | ___  _ __   __| | __ _ _   _ \n"
            + "| |\\/| |/ _ \\| '_ \\ / _` |/ _` | | | |\n"
            + "| |  | | (_) | | | | (_| | (_| | |_| |\n"
            + "|_|  |_|\\___/|_| |_|\\__,_|\\__,_|\\__, |\n"
            + "                                |___/  \n";
    private static final String LINE = "____________________________________________________________";
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.run();
    }

    private void run() {
        printWelcomeMessage();
        handleUserInput();
    }

    private void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("    Hello! I'm MONDAY\n    What can I do for you?");
        System.out.println(LINE);
    }

    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println(LINE);

            if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTaskAsNotDone(input);
            } else if (input.equalsIgnoreCase("bye")) {
                printGoodbyeMessage();
                break;
            } else {
                addTask(input);
            }

            System.out.println(LINE);
        }
    }

    private void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
    }

    private void markTaskAsDone(String input) {
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        if (isValidTaskNumber(taskNumber)) {
            tasks[taskNumber].markAsDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + tasks[taskNumber]);
        } else {
            System.out.println("    Invalid task number.");
        }
    }

    private void unmarkTaskAsNotDone(String input) {
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        if (isValidTaskNumber(taskNumber)) {
            tasks[taskNumber].markAsNotDone();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + tasks[taskNumber]);
        } else {
            System.out.println("    Invalid task number.");
        }
    }

    private void addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("    added: " + input);
    }

    private void printGoodbyeMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 0 && taskNumber < taskCount;
    }
}
