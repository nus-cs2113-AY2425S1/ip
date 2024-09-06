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
        String command = textParts[0].toLowerCase();

        boolean hasOnlyCommand = textParts.length == 1;
        if (hasOnlyCommand) {
            System.out.println("MISSING!!! The description of a "
                    + command
                    + " cannot be empty.");
            return;
        }

        String details = textParts[1];
        Task newTask;
        switch (command) {
        case "todo":
            newTask = new Todo(details);
            break;
        case "deadline":
            newTask = new Deadline(details);
            break;
        default:
            newTask = new Event(details);
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
                throw new IllegalArgumentException("NOO!!! Missing task number");
            }

            int taskIndex = Integer.parseInt(textParts[1]) - 1;
            boolean isInvalidTaskIndex = taskIndex >= numOfTasks || taskIndex < 0;
            if (isInvalidTaskIndex) {
                    throw new IllegalArgumentException("WHAT!!! This task does not exist");
            }
            return tasks[taskIndex];
        } catch (NumberFormatException e) { // from parseInt
            throw new IllegalArgumentException("HMMM... The index of the task must be an integer");
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
            System.out.println("Tell me your needs! I'm here to help!");
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
        case "deadline", "todo", "event":
            addTask(text);
            break;
        default:
            System.out.println("HUH?!? I don't recognize this command :(");
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
