import java.util.Scanner;

public class JerChatBot {

    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printEmptyInputMessage();
                continue;
            }

            String[] commands = input.split(" ", 2);
            String command = commands[0];

            switch (command) {
            case "bye":
                printExitMessage();
                return; // exits the main method and ends the program
            case "list":
                printTaskList();
                break;
            case "mark":
                handleMarkCommand(commands);
                break;
            case "unmark":
                handleUnmarkCommand(commands);
                break;
            default:
                addTask(input);
                break;
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           Welcome to Jer Chat Bot!                       ");
        System.out.println("           What can I do for you today?                   ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printEmptyInputMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Please tell me what to do next");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printExitMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" End of Program");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printTaskList() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Current Task List:");
        if (taskCount == 0) {
            System.out.println(" No tasks in your list currently.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void handleMarkCommand(String[] commands) {
        if (commands.length < 2) {
            printInvalidTaskMessage();
            return;
        }

        try {
            int taskIndex = Integer.parseInt(commands[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].markAsDone();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Marked this task as done:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                printInvalidTaskMessage();
            }
        } catch (NumberFormatException e) {
            printInvalidTaskMessage();
        }
    }

    private static void handleUnmarkCommand(String[] commands) {
        if (commands.length < 2) {
            printInvalidTaskMessage();
            return;
        }

        try {
            int taskIndex = Integer.parseInt(commands[1]) - 1;
            if (isValidTaskIndex(taskIndex)) {
                tasks[taskIndex].removeAsDone();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Marked this task as not done:");
                System.out.println(" " + tasks[taskIndex]);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                printInvalidTaskMessage();
            }
        } catch (NumberFormatException e) {
            printInvalidTaskMessage();
        }
    }

    private static void printInvalidTaskMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Invalid command or task number. Please try again.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {

            String[] parts = taskDescription.split(" ", 2);
            String categoryOfTask = parts[0];
            String taskDetails = parts.length > 1 ? parts[1] : "";
            Task newTask;

            switch (categoryOfTask) {
            case "todo":
                newTask = new ToDo(taskDetails);
                break;

            case "deadline":
                String[] deadlineParts = taskDetails.split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    printInvalidTaskMessage();
                    return;
                }
                newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;

            case "event":
                String[] eventParts = taskDetails.split(" /from | /to ", 3);
                if (eventParts.length < 3) {
                    printInvalidTaskMessage();
                    return;
                }
                newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                printInvalidTaskMessage();
                return;
            }

            tasks[taskCount] = newTask;
            taskCount++;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" added: " + newTask);
            System.out.println(" Currently you have: " + taskCount + " tasks in your list");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" Task list is full. Unable to insert more tasks.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    private static boolean isValidTaskIndex(int index) {
        return index >= 0 && index < taskCount;
    }
}
