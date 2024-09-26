package quinn.ui;

import quinn.task.Task;
import quinn.task.TaskList;

import java.util.Scanner;

/**
 * The Ui (User Interface) class handles all interactions between the Quinn task management application
 * and the user. It is responsible for displaying information to the user and reading user input.
 * This class provides methods for reading commands, displaying various messages and task information,
 * and formatting the output for better readability.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     * This constructor doesn't initialize any fields as the Ui class currently has no instance variables.
     */
    public Ui() {
    }

    /**
     * Reads a command from the user via the console.
     *
     * @return the user's input command as a trimmed string
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Command:");
        System.out.print("\t");
        return sc.nextLine().trim();
    }

    /**
     * Displays a welcome message and the Quinn logo when the application starts.
     * This method prints the welcome message and logo to the console.
     */
    public void displayWelcome() {
        String logo = "\t" + "  QQQ   U   U III N   N N   N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  NN  N NN  N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N N N N N N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N  NN N  NN " + System.lineSeparator()
                + "\t" + "  QQQ    UUU  III N   N N   N " + System.lineSeparator()
                + "\t" + "    Q                       " + System.lineSeparator()
                + "\t" + "     QQ                     " + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Quinn, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + System.lineSeparator()
                + logo
                + System.lineSeparator()
                + "\t" + "How can I help you?";

        displayResponse(welcomeMessage);
        displayLine();
    }

    /**
     * Displays an exit message when the user chooses to exit the application.
     * This method prints the farewell message to the console.
     */
    public void displayExit() {
        String exitMessage = "\t" + "Farewell. Hope to see you again soon!";
        displayResponse(exitMessage);
    }


    /**
     * Displays a given message to the user, prefixed with a horizontal line for better readability.
     *
     * @param message the message to be displayed
     */
    public void displayResponse(String message) {
        displayLine();
        System.out.println(message);
    }


    /**
     * Displays an error message to the user
     *
     * @param message the error message to be displayed
     */
    public void displayError(String message) {
        displayLine();
        System.out.println("Error Message:");
        System.out.println("\t" + "('-')? " + message);
    }

    /**
     * Displays a list of filtered tasks to the user.
     *
     * @param message the message containing the filtered task information
     */
    public void displayFilteredTasks(String message) {
        displayLine();
        System.out.println("[FILTERED TASKS]" + System.lineSeparator());
        System.out.println(message);
    }

    /**
     * Displays a horizontal line for separating different sections of output.
     */
    public void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }


    /**
     * Generates a message indicating that a task has been added to the list.
     *
     * @param task the Task that was added
     * @return a formatted string confirming the addition of the task
     */
    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Generates a message indicating that a task has been marked as done.
     *
     * @param task the Task that was marked as done
     * @return a formatted string confirming the task has been marked as done
     */
    public String taskDoneMessage(Task task) {
        return "\t" + "Roger! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Generates a message indicating that a task has been marked as not done.
     *
     * @param task the Task that was marked as not done
     * @return a formatted string confirming the task has been marked as not done
     */
    public String taskNotDoneMessage(Task task) {
        return "\t" + "OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Generates a message indicating that a task has been deleted from the list.
     *
     * @param task the Task that was deleted
     * @return a formatted string confirming the deletion of the task
     */
    public String taskDeletedMessage(Task task) {
        return "\t" + "Roger. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Generates a message indicating the current number of tasks in the list.
     *
     * @param taskList the TaskList containing the tasks
     * @return a formatted string stating the number of tasks in the list
     */
    public String numOfTasksInListMessage(TaskList taskList) {
        return "\t" + "Now you have " + taskList.getNumOfTasks()
                + (taskList.getNumOfTasks() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    /**
     * Generates a message listing all tasks currently in the task list.
     *
     * @param taskList the TaskList containing the tasks to be listed
     * @return a formatted string containing all tasks in the list
     */
    public String tasksInListMessage(TaskList taskList) {
        return "\t" + "Here"
                + (taskList.getNumOfTasks() > 1 ? " are the tasks  " : " is the task ")
                + "in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }

    /**
     * Generates a message listing all tasks that match a given keyword.
     *
     * @param taskList the TaskList containing the filtered tasks
     * @param keyword the keyword used for filtering the tasks
     * @return a formatted string containing all tasks that match the keyword
     */
    public String tasksWithKeywordMessage(TaskList taskList, String keyword) {
        return "\t" + "Here"
                + (taskList.getNumOfFilteredTasks() > 1 ? " are the matching tasks " : " is the matching task ")
                + "in your list:"
                + System.lineSeparator()
                + "\t" + "[Keyword Search: " + keyword + "]"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList;
    }
}
