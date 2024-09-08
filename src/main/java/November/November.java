package November;

import November.Exceptions.NovemberExceptions;
import November.Tasks.Deadline;
import November.Tasks.Event;
import November.Tasks.Task;
import November.Tasks.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * November is a command-line chatbot that manages tasks.
 */
public class November {

    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static final String INIT_SENTENCE = "Hello! I'm November."
            + System.lineSeparator() + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_EMPTY_MESSAGE = "Your list is currently empty.";
    private static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done: ";
    private static final String UNMARK_TASK_MESSAGE = "Ok, I've marked this task as not done yet: ";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:" + System.lineSeparator() + "  ";

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

    /**
     * Prints a numbered list of tasks with their completion status.
     *
     * @param taskList The list of tasks to print, with each task displaying its completion status.
     */
    public static void printTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println(LIST_EMPTY_MESSAGE);
            return;
        }
        int index = 0;
        System.out.println(LIST_MESSAGE);
        while (index < taskList.size()) {
            System.out.print(index + 1 + ".");
            taskList.get(index).printTask();
            index++;
        }
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The list of tasks.
     */
    private static void printTaskCount(List<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * The main method of the November chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        beginSegment();
        System.out.println(INIT_SENTENCE);
        endSegment();

        List<Task> taskList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        // Continue reading user input until the exit command is entered.
        while (!input.equals(EXIT_COMMAND)) {
            String[] sentence = {input, input};
            String command = input;
            String description = "";

            // Split the input into command and description if applicable.
            if (input.contains(" ")) {
                sentence = input.split(" ", 2);
                command = sentence[0];
                description = sentence[1];
            }

            switch (command) {
                case MARK_COMMAND:
                    try {
                        // Marks a task as complete.
                        int markIndex = Integer.parseInt(sentence[1]) - 1;
                        taskList.get(markIndex).setComplete();
                        beginSegment();
                        System.out.print(MARK_TASK_MESSAGE + System.lineSeparator() + "  ");
                        taskList.get(markIndex).printTask();
                        endSegment();
                    } catch (NumberFormatException e) {
                        beginSegment();
                        System.out.println("Please provide a valid numerical index.");
                        endSegment();
                    } catch (IndexOutOfBoundsException e) {
                        beginSegment();
                        System.out.println("Sorry, but that index is not within the list.");
                        endSegment();
                    }
                    break;
                case UNMARK_COMMAND:
                    try {
                        // Marks a task as incomplete.
                        int unmarkIndex = Integer.parseInt(sentence[1]) - 1;
                        taskList.get(unmarkIndex).setIncomplete();
                        beginSegment();
                        System.out.print(UNMARK_TASK_MESSAGE + System.lineSeparator() + "  ");
                        taskList.get(unmarkIndex).printTask();
                        endSegment();
                    } catch (NumberFormatException e) {
                        beginSegment();
                        System.out.println("Please provide a valid numerical index.");
                        endSegment();
                    } catch (IndexOutOfBoundsException e) {
                        beginSegment();
                        System.out.println("Sorry, but that index is outside the list.");
                        endSegment();
                    }
                    break;
                case LIST_COMMAND:
                    // Prints a list of all tasks, indicating their completion status.
                    beginSegment();
                    printTaskList(taskList);
                    endSegment();
                    break;
                case TODO_COMMAND:
                    try {
                        // Adds a new todo task to the task list.
                        Todo todoTask = NovemberExceptions.validTodo(description);
                        taskList.add(todoTask);
                        beginSegment();
                        System.out.print(ADD_TASK_MESSAGE);
                        todoTask.printTask();
                        printTaskCount(taskList);
                        endSegment();
                    } catch (IllegalArgumentException e) {
                        beginSegment();
                        System.out.println(e.getMessage());
                        endSegment();
                    }
                    break;
                case DEADLINE_COMMAND:
                    try {
                        // Adds a new deadline task to the task list.
                        Deadline deadlineTask = NovemberExceptions.validDeadline(description);
                        taskList.add(deadlineTask);
                        beginSegment();
                        System.out.print(ADD_TASK_MESSAGE);
                        deadlineTask.printTask();
                        printTaskCount(taskList);
                        endSegment();
                    } catch (IllegalArgumentException e) {
                        beginSegment();
                        System.out.println(e.getMessage());
                        endSegment();
                    }
                    break;
                case EVENT_COMMAND:
                    try {
                        // Adds a new event task to the task list.
                        Event eventTask = NovemberExceptions.validEvent(description);
                        taskList.add(eventTask);
                        beginSegment();
                        System.out.print(ADD_TASK_MESSAGE);
                        eventTask.printTask();
                        printTaskCount(taskList);
                        endSegment();
                    } catch (IllegalArgumentException e) {
                        beginSegment();
                        System.out.println(e.getMessage());
                        endSegment();
                    }
                    break;
                default:
                    // Response to unrecognized inputs.
                    beginSegment();
                    System.out.println("I'm sorry, I don't know what that means.");
                    endSegment();
                    break;
            }

            input = scan.nextLine();
        }

        beginSegment();
        System.out.println(EXIT_MESSAGE);
        endSegment();
    }
}
