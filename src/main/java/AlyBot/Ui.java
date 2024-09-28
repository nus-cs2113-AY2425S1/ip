package AlyBot;

import Task.Task;

import java.util.Scanner;

/**
 * The Ui class manages user interactions in the AlyBot application.
 * This includes displaying messages, reading user commands, and showing task lists.
 */
public class Ui {
    private Scanner in = new Scanner(System.in);
    private final String LINE_SEPARATOR = "=".repeat(112);

    /**
     * Prints a line separator to the console.
     */
    public void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a welcome message along with the AlyBot logo.
     */
    public void showWelcome() {
        String LOGO = "    _      _     _   _\n"
                + "   / \\    | |   \\ \\ / /\n"
                + "  / _ \\   | |    \\ V / \n"
                + " / ___ \\  | |__   | |  \n"
                + "/_/   \\_\\ |____|  |_|  \n";
        System.out.println("Hello, my name is \n"+ LOGO);
        printLine();
    }

    /**
     * Reads a command input from the user.
     *
     * @return The trimmed command input as a string.
     */
    public String readCommand() {
        String input = in.nextLine();
        return input.trim();
    }

    /**
     * Displays the starting message prompting the user for input.
     */
    public void startingMessage() {
        System.out.println("I'm hungry, what do you want?");
        printLine();
        help();
    }

    /**
     * Displays a list of possible commands to the user.
     */
    public void help() {
        System.out.println("Possible commands: \n"
                + "1. 'echo' followed by input to receive echo\n"
                + "2. 'list' to see your list of tasks\n"
                + "3. 'todo/deadline/event' to add that type of task\n"
                + "4. 'mark/unmark' with a number to toggle that task's status\n"
                + "5. 'delete' with a number to delete that task\n"
                + "6. 'filter' with a date to see all tasks scheduled on that date\n"
                + "7. 'find' with a description to see all tasks containing that description\n"
                + "8. 'help' to see the command list\n"
                + "9. 'exit' to exit");
    }

    /**
     * Displays a specified error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param instructions The task description that has been added.
     */
    public void addMessage(String instructions) {
        System.out.println("Added this task: " + instructions.trim());
    }

    /**
     * Displays the current number of tasks in the task list.
     *
     * @param taskSize The number of tasks in the list.
     */
    public void showTaskSize(int taskSize) {
        System.out.println("You have " + taskSize + " tasks in your list now.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList object containing the tasks to display.
     * @throws AlyException If an error occurs while displaying the task list.
     */
    public void showList(TaskList taskList) throws AlyException {
        int count = 1;
        System.out.println("Your task list:");
        try {
            for (Task listItem : taskList.getList()) {
                System.out.println(count + "." + listItem);
                count++;
            }
        } catch (Exception e) {
            throw new AlyException("I am not sure what happened lmao", e);
        }
        if (count == 1) {
            System.out.println("No tasks means can lepak!");
        } else {
            System.out.println("Wah shag, good luck with your tasks bro!");
        }
    }

    /**
     * Displays a message indicating the status change of a task.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param indexNumToToggle The index of the task that has changed status.
     * @param firstWord The command word ("mark" or "unmark").
     * @throws AlyException If an error occurs while showing the status change.
     */
    public void showStatusChange(TaskList taskList, int indexNumToToggle, String firstWord) throws AlyException {
        if (firstWord.equals("mark")) {
            System.out.println("\"" + taskList.findTask(indexNumToToggle).getDescription() + "\" marked as done!");
        } else if (firstWord.equals("unmark")) {
            System.out.println("\"" + taskList.findTask(indexNumToToggle).getDescription() + "\" marked as undone!");
        } else {
            throw new AlyException("Something went wrong!");
        }
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param indexNumToDelete The index of the task that has been deleted.
     */
    public void showDelete(TaskList taskList, int indexNumToDelete) {
        System.out.println("Deleted this task: " + taskList.findTask(indexNumToDelete).getDescription());
    }
}