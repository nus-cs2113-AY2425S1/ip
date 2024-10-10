package november.ui;

import november.tasks.Task;

import java.util.List;

import static november.messages.Messages.EXIT_MESSAGE;
import static november.messages.Messages.INIT_SENTENCE;
import static november.messages.Messages.INVALID_INPUT_MESSAGE;
import static november.messages.Messages.LIST_EMPTY_MESSAGE;
import static november.messages.Messages.LIST_MESSAGE;
import static november.messages.Messages.SEPARATOR;

public class Ui {
    /**
     * Prints a line of underscores to mark the start of a print segment.
     */
    public static void beginSegment() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a line of underscores followed by a newline to mark the end of a print segment.
     */
    public static void endSegment() {
        System.out.println(SEPARATOR + System.lineSeparator());
    }

    public static void printGreeting() {
        beginSegment();
        System.out.println(INIT_SENTENCE); // Print the initial greeting
        endSegment();
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to print.
     */
    public static void printTaskList(List<Task> taskList) {
        beginSegment();
        if (taskList.isEmpty()) {
            System.out.println(LIST_EMPTY_MESSAGE);
            endSegment();
            return;
        }
        int index = 0;
        System.out.println(LIST_MESSAGE);
        while (index < taskList.size()) {
            System.out.print(index + 1 + ". ");
            taskList.get(index).printTask(); // Print each task
            index++;
        }
        endSegment();
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The list of tasks.
     */
    public static void printTaskCount(List<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the message for unrecognized input.
     */
    public static void printUnrecognizedInputMessage() {
        beginSegment();
        System.out.println(INVALID_INPUT_MESSAGE); // Print the unrecognized input message
        endSegment();
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        beginSegment();
        System.out.println(EXIT_MESSAGE); // Print the exit message
        endSegment();
    }
}
