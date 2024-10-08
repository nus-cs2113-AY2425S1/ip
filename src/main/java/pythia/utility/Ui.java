package pythia.utility;

import java.util.Scanner;

/**
 * A class responsible for handling user interaction and displaying information to the console.
 * It provides methods to display responses, get user input, and format task lists for output.
 */
public class Ui {
    private static Scanner scanner;;

    /**
     * Initializes the scanner object for reading user input.
     */
    public void init() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a formatted response to the console with line separators.
     *
     * @param text The message to be printed.
     */
    public void printResponse(String text) {
        String lineSeparator = "____________________________________________________________";
        text = lineSeparator +  "\n" + text + "\n" + lineSeparator + "\n";
        String formattedText = text.replaceAll("(?m)^", "\t");
        System.out.print(formattedText);
    }

    /**
     * Retrieves a user's input from the console.
     *
     * @return The raw string input from the user.
     */
    public String getRequest() {
        return scanner.nextLine();
    }

    /**
     * Prints a response for when a task is added, formatting the text to be all lowercase.
     *
     * @param text The message to be printed, typically confirming a task addition.
     */
    public void printAddedTask(String text) {
        printResponse(text.toLowerCase());
    }

    /**
     * Prints the entire task list to the console, including optional comments
     * before and after the list.
     *
     * @param taskList The list of tasks to be printed.
     * @param commentBefore An optional comment to be printed before the task list.
     * @param commentAfter An optional comment to be printed after the task list.
     */
    public void printTaskList(TaskList taskList, String commentBefore, String commentAfter) {
        StringBuilder response = new StringBuilder();
        if (commentBefore != null && !commentBefore.isEmpty()) {
            response.append(commentBefore).append("\n");
        }

        String taskListAsString = taskList.toString();
        if (taskListAsString != null && !taskListAsString.isEmpty()) {
            response.append(taskListAsString);
        }

        if (commentAfter != null && !commentAfter.isEmpty()) {
            response.append("\n").append(commentAfter);
        }

        printResponse(response.toString());
    }
}
