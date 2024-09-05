import java.util.Scanner;

public class Echo {
    private static final String SEPARATOR = "_".repeat(30);
    private static final int TODO_WORD_LENGTH = 5;
    private static final int DEADLINE_WORD_LENGTH = 9;
    private static final int DEADLINE_DUE_DATE_OFFSET = 5;
    private static final int EVENT_WORD_LENGTH = 6;
    private static final int START_WORD_LENGTH = 7;
    private static final int END_WORD_LENGTH = 5;

    public static void main(String[] args) {
        // Utilize Scanner object to allow user input
        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();

        // Define the greeting message and the chatbot name as variables
        String chatbotName = "Echo";
        String greetingMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

        // Define the exit message as variable
        String exitMessage = "Bye. Hope to see you again soon!";

        // Print the greeting message from Echo
        printGreeting(greetingMessage);

        String userInput;
        do {
            // Read user input
            userInput = scanner.nextLine();

            // Handles different user inputs for list, bye, mark, and unmark
            if (userInput.equalsIgnoreCase("list")) {
                printTaskList(taskList);
            } else if (userInput.startsWith("mark ")) {
                handleMarking(userInput, taskList);
            } else if (userInput.startsWith("unmark ")) {
                handleUnmarking(userInput, taskList);
            } else if (!userInput.equalsIgnoreCase("bye")) {
                addTask(userInput, taskList);
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        // Print the exit message from Echo
        printExitMessage(exitMessage);
    }

    private static void printGreeting(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(TaskList taskList) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.displayTasks());
        System.out.println(SEPARATOR);
    }

    private static void handleMarking(String userInput, TaskList taskList) {
        int taskNumber = Integer.parseInt(userInput.substring(5).trim());
        System.out.println(SEPARATOR);
        System.out.println(taskList.markTaskAsDone(taskNumber));
        System.out.println(SEPARATOR);
    }

    private static void handleUnmarking(String userInput, TaskList taskList) {
        int taskNumber = Integer.parseInt(userInput.substring(7).trim());
        System.out.println(SEPARATOR);
        System.out.println(taskList.markTaskAsNotDone(taskNumber));
        System.out.println(SEPARATOR);
    }

    private static void addTask(String userInput, TaskList taskList) {
        userInput = userInput.trim();

        if (userInput.startsWith("todo ")) {
            addToDoTask(userInput, taskList);
        } else if (userInput.startsWith("deadline ")) {
            addDeadlineTask(userInput, taskList);
        } else if (userInput.startsWith("event ")) {
            addEventTask(userInput, taskList);
        }
    }

    private static void addToDoTask(String userInput, TaskList taskList) {
        String description = userInput.substring(TODO_WORD_LENGTH).trim();
        ToDo newTask = new ToDo(description);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void addDeadlineTask(String userInput, TaskList taskList) {
        int byIndex = userInput.indexOf(" /by ");
        String description = userInput.substring(DEADLINE_WORD_LENGTH, byIndex).trim();
        String dueDate = userInput.substring(byIndex + DEADLINE_DUE_DATE_OFFSET).trim();
        Deadline newTask = new Deadline(description, dueDate);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void addEventTask(String userInput, TaskList taskList) {
        int startIndex = userInput.indexOf(" /from ");
        int endIndex = userInput.indexOf(" /to ", startIndex + START_WORD_LENGTH);
        String description = userInput.substring(EVENT_WORD_LENGTH, startIndex).trim();
        String start = userInput.substring(startIndex + START_WORD_LENGTH, endIndex).trim();
        String end = userInput.substring(endIndex + END_WORD_LENGTH).trim();
        Event newTask = new Event(description, start, end);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void printTaskAddedMessage(Task task, int totalTasks) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private static void printExitMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }
}
