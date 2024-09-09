import exception.EchoException;
import task.*;

import java.util.Scanner;

public class Echo {
    private static final String SEPARATOR = "_".repeat(30);
    private static final int TODO_WORD_LENGTH = 5;
    private static final int MARK_WORD_LENGTH = 5;
    private static final int UNMARK_WORD_LENGTH = 7;
    private static final int DEADLINE_WORD_LENGTH = 9;
    private static final int DEADLINE_DUE_DATE_OFFSET = 5;
    private static final int EVENT_WORD_LENGTH = 6;
    private static final int START_WORD_LENGTH = 7;
    private static final int END_WORD_LENGTH = 5;

    public static void runEcho() {
        // Utilize Scanner object to allow user input
        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();

        // Define the greeting message and the chatbot name as variables
        String chatbotName = "Echo";
        String greetingMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

        // Define the exit message as a variable
        String exitMessage = "Bye. Hope to see you again soon!";

        // Print the greeting message from Echo
        printGreeting(greetingMessage);

        String userInput;
        do {
            // Read user input
            userInput = scanner.nextLine();

            // Handles different user inputs for list, bye, mark, and unmark
            processUserInput(userInput, taskList);
        } while (!userInput.equalsIgnoreCase("bye"));

        // Print the exit message from Echo
        printExitMessage(exitMessage);
    }

    public static void processUserInput(String userInput, TaskList taskList) {
        if (userInput.equalsIgnoreCase("list")) {
            printTaskList(taskList);
        } else if (userInput.startsWith("mark ")) {
            handleMarking(userInput, taskList);
        } else if (userInput.startsWith("unmark ")) {
            handleUnmarking(userInput, taskList);
        } else if (userInput.startsWith("todo ")) {
            String description = userInput.substring(TODO_WORD_LENGTH).trim();
            if (description.isEmpty()) {
                printErrorMessage(EchoException.todoDescriptionMissing());
            } else {
                addToDoTask(userInput, taskList);
            }
        } else if (userInput.startsWith("deadline ")) {
            String description = userInput.substring(DEADLINE_WORD_LENGTH).trim();
            if (description.isEmpty()) {
                printErrorMessage(EchoException.deadlineDescriptionMissing());
            } else {
                addDeadlineTask(userInput, taskList);
            }
        } else if (userInput.startsWith("event ")) {
            String description = userInput.substring(EVENT_WORD_LENGTH).trim();
            if (description.isEmpty()) {
                printErrorMessage(EchoException.eventDescriptionMissing());
            } else {
                addEventTask(userInput, taskList);
            }
        } else if (!userInput.equalsIgnoreCase("bye")) {
            printErrorMessage(EchoException.unknownCommand());
        }
    }

    /**
     * Method where Echo greets the user.
     *
     * @param message The start message displayed on CLI.
     */
    private static void printGreeting(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Method to display all the tasks.
     *
     * @param taskList An array of tasks.
     */
    private static void printTaskList(TaskList taskList) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.displayTasks());
        System.out.println(SEPARATOR);
    }

    /**
     * Puts the icon X when called to mark task as done.
     *
     * @param userInput Inputs the task number which will be marked as done.
     * @param taskList  Outputs updated array of task.
     */
    private static void handleMarking(String userInput, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(userInput.substring(MARK_WORD_LENGTH).trim());

            if (taskNumber < 1 || taskNumber > taskList.getTaskNumber()) {
                printErrorMessage(EchoException.taskNumberOutOfRange());
                return;
            }
            System.out.println(SEPARATOR);
            System.out.println(taskList.markTaskAsDone(taskNumber));
            System.out.println(SEPARATOR);
        } catch (NumberFormatException e) {
            printErrorMessage(EchoException.invalidTaskNumberFormat());
        }
    }

    /**
     * Removes the icon X when called to unmark task as done.
     *
     * @param userInput Inputs the task number which will be unmarked as done.
     * @param taskList  Outputs updated array of task.
     */
    private static void handleUnmarking(String userInput, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(userInput.substring(UNMARK_WORD_LENGTH).trim());

            if (taskNumber < 1 || taskNumber > taskList.getTaskNumber()) {
                printErrorMessage(EchoException.taskNumberOutOfRange());
                return;
            }
            System.out.println(SEPARATOR);
            System.out.println(taskList.markTaskAsNotDone(taskNumber));
            System.out.println(SEPARATOR);
        } catch (NumberFormatException e) {
            printErrorMessage(EchoException.invalidTaskNumberFormat());
        }
    }

    /**
     * Add different kinds of tasks.
     *
     * @param userInput Input task type and description.
     * @param taskList  Outputs updated array of task.
     */
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

    /**
     * task.ToDo type of task will be added.
     *
     * @param userInput Inputs task.ToDo task details.
     * @param taskList  Outputs updated array of task.
     */
    private static void addToDoTask(String userInput, TaskList taskList) {
        String description = userInput.substring(TODO_WORD_LENGTH).trim();
        ToDo newTask = new ToDo(description);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    /**
     * task.Deadline type of task will be added.
     *
     * @param userInput Inputs task.Deadline task details.
     * @param taskList  Outputs updated array of task.
     */
    private static void addDeadlineTask(String userInput, TaskList taskList) {
        int byIndex = userInput.indexOf(" /by ");
        String description = userInput.substring(DEADLINE_WORD_LENGTH, byIndex).trim();
        String dueDate = userInput.substring(byIndex + DEADLINE_DUE_DATE_OFFSET).trim();
        Deadline newTask = new Deadline(description, dueDate);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    /**
     * task.Event type of task will be added.
     *
     * @param userInput Inputs task.Event task details.
     * @param taskList  Outputs updated array of task.
     */
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

    /**
     * Method to handle confirmation message.
     *
     * @param task       task.Task type and details.
     * @param totalTasks Count of the number of task.
     */
    private static void printTaskAddedMessage(Task task, int totalTasks) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Method to handle the exit message.
     *
     * @param message Displays exit message by Echo.
     */
    private static void printExitMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    private static void printErrorMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! " + message);
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
        runEcho();
    }
}