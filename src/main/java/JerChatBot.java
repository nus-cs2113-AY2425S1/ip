import java.util.Scanner;
import exception.EmptyDescriptionException;
import exception.UnknownCommandException;
import exception.InvalidTaskNumberException;
import exception.TaskListFullException;
import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;

public class JerChatBot {

    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    // define constant for task categories
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

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

            try {
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
                case TODO:
                case DEADLINE:
                case EVENT:
                    addTask(input);
                    break;
                default:
                    throw new UnknownCommandException(" Sorry, could not understand the command given.");
                }
            } catch (EmptyDescriptionException | UnknownCommandException | InvalidTaskNumberException | TaskListFullException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" " + e.getMessage());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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

    private static void handleMarkCommand(String[] commands) throws InvalidTaskNumberException {
        if (commands.length < 2) {
            throw new InvalidTaskNumberException("Invalid command or task number. Please try again.");
        }

        int taskIndex = parseTaskIndex(commands[1]);
        if (!isValidTaskIndex(taskIndex)) {
            throw new InvalidTaskNumberException("Invalid command or task number. Please try again.");
        }

        tasks[taskIndex].markAsDone();
        printTaskMarkedAsDoneMessage(taskIndex);
    }

    private static int parseTaskIndex(String command) {
        try {
            return Integer.parseInt(command) - 1;
        } catch (NumberFormatException e) {
            return -1; // Return an invalid index if parsing fails
        }
    }

    private static void printTaskMarkedAsDoneMessage(int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Marked this task as done:");
        System.out.println(" " + tasks[taskIndex]);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void handleUnmarkCommand(String[] commands) throws InvalidTaskNumberException{
        if (commands.length < 2) {
            throw new InvalidTaskNumberException("Invalid command or task number. Please try again.");
        }

        int taskIndex = parseTaskIndex(commands[1]);
        if (!isValidTaskIndex(taskIndex)) {
            throw new InvalidTaskNumberException("Invalid command or task number. Please try again.");
        }

        tasks[taskIndex].removeAsDone();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" Marked this task as not done:");
        System.out.println(" " + tasks[taskIndex]);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void addTask(String taskDescription) throws EmptyDescriptionException, TaskListFullException, UnknownCommandException  {
        if (taskCount >= MAX_TASKS) {
            throw new TaskListFullException("Task list is full. Unable to insert more tasks.");
        }

        String[] parts = taskDescription.split(" ", 2);
        String categoryOfTask = parts[0];
        String taskDetails = parts.length > 1 ? parts[1] : "";

        if (taskDetails.isEmpty()) {
            throw new EmptyDescriptionException("The description of a " + categoryOfTask + " cannot be empty.");
        }

        Task newTask = createTask(categoryOfTask, taskDetails);
        if (newTask == null) {
            throw new UnknownCommandException("Invalid command or task type.");
        }

        tasks[taskCount] = newTask;
        taskCount++;
        printTaskAddedMessage(newTask);
    }

    private static Task createTask(String categoryOfTask, String taskDetails) {
        switch (categoryOfTask) {
        case TODO:
            return new ToDo(taskDetails);
        case DEADLINE:
            return createDeadlineTask(taskDetails);
        case EVENT:
            return createEventTask(taskDetails);
        default:
            return null;
        }
    }

    private static Task createDeadlineTask(String taskDetails) {
        String[] deadlineParts = taskDetails.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            return null;
        }
        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }

    private static Task createEventTask(String taskDetails) {
        String[] eventParts = taskDetails.split(" /from | /to ", 3);
        if (eventParts.length < 3) {
            return null;
        }
        return new Event(eventParts[0], eventParts[1], eventParts[2]);
    }

    private static void printTaskAddedMessage(Task newTask) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" added: " + newTask);
        System.out.println(" Currently you have: " + taskCount + " tasks in your list");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static boolean isValidTaskIndex(int index) {
        return index >= 0 && index < taskCount;
    }
}
