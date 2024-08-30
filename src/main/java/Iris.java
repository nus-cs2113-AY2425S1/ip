import java.util.Scanner;

public class Iris {
    public static Task[] tasks = new Task[100];
    public static int numOfTasks = 0;

    public static void printDivider() {
        System.out.println("---------------------------------------------");
    }

    public static void listTasks() {
        if (numOfTasks == 0) {
            System.out.println("No tasks added.");
            return;
        }
        for (int i = 0; i < numOfTasks; i++) {
            Task currentTask = tasks[i];
            System.out.println((i + 1) + "."
                    + currentTask.getStatusIcon()
                    + currentTask.description);
        }
    }

    public static void addTask(String text) {
        tasks[numOfTasks] = new Task(text);
        System.out.println("Added: " + text);
        numOfTasks++;
    }

    public static void editTask(String text) {
        try {
            String numbersInText = text.replaceAll("[^0-9]", "");
            int taskIndex = Integer.parseInt(numbersInText);
            boolean isInvalidTaskIndex = taskIndex > numOfTasks || taskIndex < 1;
            if (isInvalidTaskIndex) {
                System.out.println("The task does not exist");
                return;
            }

            Task taskToEdit = tasks[(taskIndex) - 1];
            if (text.contains("unmark")) {
                taskToEdit.unmarkFromDone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + "[ ] "
                        + taskToEdit.description);
            } else {
                taskToEdit.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                        + "[X] "
                        + taskToEdit.description);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        }
    }

    public static boolean chat(String text) {
        printDivider();
        if (text.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else if (text.equalsIgnoreCase("list")) {
            listTasks();
        } else if (text.contains("mark")) {
            editTask(text);
        } else if (text.isEmpty()) {
            System.out.println("Do you need any further assistance?");
        } else {
            addTask(text);
        }
        printDivider();
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Iris!\n"
                + "What can I do for you?");
        printDivider();
        boolean isEnded = false;
        Scanner in = new Scanner(System.in);
        while (!isEnded) {
            String line = in.nextLine();
            isEnded = chat(line);
        }
    }
}
