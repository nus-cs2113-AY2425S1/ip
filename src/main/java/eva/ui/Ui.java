package eva.ui;

import eva.tasks.Task;

import java.util.Scanner;
import java. util. ArrayList;

public class Ui {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    public static final String BANNER = """
                 ________ _______ _____   ______ _______ _______ _______\s
                |  |  |  |    ___|     |_|      |       |   |   |    ___|
                |  |  |  |    ___|       |   ---|   -   |       |    ___|
                |________|_______|_______|______|_______|__|_|__|_______|
                """;
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed to the user
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays welcome message when users enter the program.
     */
    public void showWelcomeMessage() {
        showMessage(HORIZONTAL_LINE);
        showMessage(BANNER);
        showMessage("Hello! I'm Eva!");
        showMessage("What can I do for you?");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Displays goodbye messages when users exit the program.
     */
    public void showGoodbyeMessage() {
        showMessage("Bye. Hope to see you again soon!");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed to the user.
     */
    public void showErrorMessage(String message) {
        showMessage(message);
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays message that a task is marked.
     *
     * @param description The description of the task that is marked.
     */
    public void showTaskAsDone(String description) {
        showMessage("Great! This task is marked as done: ");
        showMessage(description);
        showMessage("Well done! ;)");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Displays message to show that the task has been unmarked.
     *
     * @param description The description of the task that is unmarked.
     */
    public void showTaskAsNotDone (String description) {
        showMessage("Ok, This task is marked as not done yet: ");
        showMessage(description);
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Displays message that a task is deleted.
     * It will also show the number of tasks remaining.
     *
     * @param taskDescription The description of the task that is deleted.
     * @param taskNumber The task number of task deleted based 0.
     * @param remainingTasks The number of task remaining in the task list.
     */
    public void showDeleteTask (String taskDescription, int taskNumber, int remainingTasks) {
        showMessage("Okay. I have deleted task " + (taskNumber + 1) + ".");
        showMessage(taskDescription);
        showMessage("Now you have " + remainingTasks + " tasks in the list.");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Shows message that a new todo task is added.
     * Show the total number of task in the list.
     *
     * @param todoDescription The description of the todo task added.
     * @param count The number of tasks in the list.
     */
    public void showAddTodo (String todoDescription, int count) {
        showMessage("Okay, I've added this todo: ");
        showMessage(todoDescription);
        showMessage("Now you have " + count + " tasks in the list.");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Shows message that a new deadline task is added.
     * Shows the total number of tasks in task list.
     *
     * @param deadlineDescription The description of deadline task added.
     * @param count Total number of tasks in the task list.
     */
    public void showAddDeadline (String deadlineDescription, int count) {
        showMessage("Okay, I've added this deadline: ");
        showMessage(deadlineDescription);
        showMessage("Now you have " + count + " tasks in the list.");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Shows message that a new event task is added.
     * Shows total number of task in the task list.
     *
     * @param eventDescription The description of the event task added.
     * @param count The total number of tasks in the task list.
     */
    public void showAddEvent (String eventDescription, int count) {
        showMessage("Okay, I've added this event: ");
        showMessage(eventDescription);
        showMessage("Now you have " + count + " tasks in the list.");
        showMessage(HORIZONTAL_LINE);
    }

    /**
     * Displays all the tasks in the task list.
     * If the task list is empty, it displays a message indicating the list is empty.
     * Otherwise, it will loop through and displays each task.
     *
     * @param tasks An ArrayList containing the tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("Your task list is currently empty.");
            return;
        }

        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showMessage((i + 1) + ". " + tasks.get(i).toString());
        }
        showMessage(HORIZONTAL_LINE);
    }
}
