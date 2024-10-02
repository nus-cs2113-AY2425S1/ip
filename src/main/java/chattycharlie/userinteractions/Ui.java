package chattycharlie.userinteractions;

import chattycharlie.TaskList;
import chattycharlie.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the user.
 * The <code>Ui</code> class handles input from the user and displays messages.
 */
public class Ui {

    /**
     * Gets the user's input from the console.
     *
     * @return the user's input as a string.
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(StringDesign.YOU);
        String userInput = scanner.nextLine();
        System.out.println(StringDesign.LINE);
        return userInput;
    }

    /**
     * Displays the greeting message and logo when ChattyCharlie starts.
     */
    public void displayGreetings(){
        System.out.println(StringDesign.LOGO + StringDesign.CHARLIE + StringDesign.GREETING);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task the task that has been added.
     */
    public void displayTaskAdded(Task task) {
        System.out.println(StringDesign.SPACE + "Added task: " + task + System.lineSeparator() + StringDesign.LINE);
    }

    /**
     * Displays the details of a given task.
     *
     * @param task the task to be displayed.
     */
    public void displayTask(Task task) {
        System.out.println(StringDesign.SPACE + task + System.lineSeparator() + StringDesign.LINE);
    }

    /**
     * Displays the entire list of tasks.
     *
     * @param list the <code>TaskList</code> containing all tasks.
     */
    public void displayList(TaskList list) {
        list.printList();
    }

    /**
     * Displays a task as part of a list with its corresponding index number.
     *
     * @param task the task to be displayed.
     * @param number the index number of the task in the list.
     */
    public void displayTaskInList(Task task, int number) {
        System.out.println(StringDesign.SPACE + number +". " + task);
    }

    /**
     * Displays an error message.
     *
     * @param error the error message to be displayed.
     */
    public void displayError(String error) {
        System.out.println(error + System.lineSeparator() + StringDesign.LINE);
    }

    /**
     * Displays a message indicating that the task has been marked or unamrked and
     * the number of remaining tasks after marking or unmarking a task.
     *
     * @param line the message to be displayed.
     * @param remainingTask the number of tasks remaining.
     */
    public void displayMarkingText(String line, int remainingTask) {
        System.out.println(StringDesign.SPACE + line  + remainingTask + " to go!");
    }

    /**
     * Displays a message indicating that a task has been deleted along with the number of remaining tasks.
     *
     * @param line the message to be displayed.
     * @param remainingTask the number of tasks remaining.
     */
    public void displayDeletedTask(String line, int remainingTask) {
        System.out.println(StringDesign.SPACE + line + remainingTask + " to go!");
    }

    /**
     * Displays the header for the task list, including the number of remaining tasks.
     *
     * @param remainingTask the number of tasks remaining.
     */
    public void displayListHeader(int remainingTask) {
        System.out.println("Task List:");
        System.out.println("pending Task: " + remainingTask);
    }

    /**
     * Displays a line separator.
     */
    public void displayLine() {
        System.out.println(StringDesign.LINE);
    }

    /**
     * Displays the header for a task list with a specified parameter for the tasks in the list.
     * Used in the command PRINT and FIND
     */
    public void displaySearchList() {
        System.out.println("Tasks found:");
    }
}
