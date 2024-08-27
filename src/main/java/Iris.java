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
        } else {
            for (int i = 0; i < numOfTasks && tasks[i] != null; i++) {
                Task currentTask = tasks[i];
                System.out.println((i + 1) + "." + currentTask.getStatusIcon() + currentTask.description);
            }
        }
    }

    public static void addTask(String text) {
        tasks[numOfTasks] = new Task(text);
        System.out.println("Added: " + text);
        numOfTasks++;
    }

    public static void editTask(String text) {
        try {
            int numbersInText = Integer.parseInt(text.replaceAll("[^0-9]", ""));
            if (numbersInText > numOfTasks || numbersInText < 1) {
                System.out.println("The task does not exist");
            } else {
                Task taskToEdit = tasks[(numbersInText) - 1];
                if (text.contains("unmark")) {
                    taskToEdit.unmarkFromDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + taskToEdit.description);
                } else {
                    taskToEdit.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[X] " + taskToEdit.description);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        }
    }

    public static boolean chat(String text) {
        printDivider();
        boolean isEnded = false;
        if (text.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            isEnded = true;
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
        return isEnded;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Iris!\n" + "What can I do for you?"); // Greets
        printDivider();
        boolean isEnded = false;
        Scanner in = new Scanner(System.in); // Initialisation
        while (!isEnded) {
            String line = in.nextLine();
            isEnded = chat(line);
        }
    }
}
