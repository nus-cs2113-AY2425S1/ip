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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            Task currentTask = tasks[i];
            System.out.println((i + 1) 
                    + "."
                    + currentTask);
        }
    }

    public static void addTask(String text) {
        String[] textParts = text.split(" ", 2);
        boolean hasOnlyCommand = textParts.length == 1;
        if (hasOnlyCommand) {
            System.out.println("Invalid command");
            return;
        }

        String command = textParts[0].toLowerCase();
        String details = textParts[1];
        Task newTask;
        switch (command) {
        case "todo":
            newTask = new Todo(details);
            break;
        case "deadline":
            newTask = new Deadline(details);
            break;
        case "event":
            newTask = new Event(details);
            break;
        default:
            System.out.println("Invalid Command");
            return;
        }
        tasks[numOfTasks++] = newTask;
        printAddTaskMessage(newTask);
    }

    private static void printAddTaskMessage(Task newTask) {
        System.out.println("Got it. I've added this task:\n"
                + newTask
                + "\nNow you have "
                + numOfTasks
                + " tasks in the list");
    }

    public static void changeTaskStatus(String text, boolean status) {
        try {
            Task taskToChange = getTaskToChangeStatus(text);
            taskToChange.mark(status);
            printChangeTaskStatusMessage(taskToChange, status);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task getTaskToChangeStatus(String text) {
        try {
            String[] textParts = text.split(" ");
            if (textParts.length == 1) {
                throw new IllegalArgumentException("Missing task number");
            }
            int taskIndex = Integer.parseInt(textParts[1]) - 1;
            boolean isInvalidTaskIndex = taskIndex >= numOfTasks || taskIndex < 0;
            if (isInvalidTaskIndex) {
                throw new IllegalArgumentException("Please provide a valid task number.");
            }
            return tasks[taskIndex];
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please provide a valid task number.");
        }
    }

    public static void printChangeTaskStatusMessage(Task task, boolean status) {
        if (status) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

    public static boolean chat(String text) {
        String command = text.split(" ")[0].toLowerCase();
        printDivider();
        switch (command) {
        case "":
            System.out.println("Do you need any further assistance?");
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        case "list":
            listTasks();
            break;
        case "mark":
            changeTaskStatus(text, true);
            break;
        case "unmark":
            changeTaskStatus(text, false);
            break;
        default:
            addTask(text);
        }
        printDivider();
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Iris!\nWhat can I do for you?");
        printDivider();
        boolean isEnded = false;
        Scanner in = new Scanner(System.in);
        while (!isEnded) {
            String line = in.nextLine();
            isEnded = chat(line);
        }
    }
}
