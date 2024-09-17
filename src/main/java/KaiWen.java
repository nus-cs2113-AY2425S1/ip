import java.util.Scanner;

public class KaiWen {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm KaiWen");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            try {
                handleInput(input);
            } catch (KaiException e) {
                printLine();
                System.out.println(" OOPS!!! " + e.getMessage());
                printLine();
            }

            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }
        }

        scanner.close();
    }

    public static void handleInput(String input) throws KaiException {
        if (input.equals("list")) {
            handleListCommand();
        } else if (input.startsWith("todo")) {
            handleTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            handleDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            handleEventCommand(input);
        } else if (input.startsWith("mark")) {
            handleMarkCommand(input);
        } else if (input.startsWith("unmark")) {
            handleUnmarkCommand(input);
        } else {
            throw new KaiException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void handleTodoCommand(String input) throws KaiException {
        if (input.trim().equals("todo")) {
            throw new KaiException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printAddedTask(tasks[taskCount - 1]);
    }

    public static void handleDeadlineCommand(String input) throws KaiException {
        if (input.trim().equals("deadline")) {
            throw new KaiException("The description of a deadline cannot be empty.");
        }
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new KaiException("The deadline needs a description and a '/by' date.");
        }
        tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
        taskCount++;
        printAddedTask(tasks[taskCount - 1]);
    }

    public static void handleEventCommand(String input) throws KaiException {
        if (input.trim().equals("event")) {
            throw new KaiException("The description of an event cannot be empty.");
        }
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new KaiException("The event needs a description, a '/from' time, and a '/to' time.");
        }
        String[] timeParts = parts[1].split(" /to ");
        tasks[taskCount] = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        taskCount++;
        printAddedTask(tasks[taskCount - 1]);
    }

    public static void handleListCommand() {
        printLine();
        if (taskCount == 0) {
            System.out.println(" No tasks to display!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        printLine();
    }

    public static void handleMarkCommand(String input) throws KaiException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KaiException("You must specify a task number to mark.");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= taskCount) {
            throw new KaiException("Task number is out of range.");
        }
        tasks[taskNumber].markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[taskNumber]);
        printLine();
    }

    public static void handleUnmarkCommand(String input) throws KaiException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KaiException("You must specify a task number to unmark.");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= taskCount) {
            throw new KaiException("Task number is out of range.");
        }
        tasks[taskNumber].markAsNotDone();
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[taskNumber]);
        printLine();
    }

    public static void printAddedTask(Task task) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
