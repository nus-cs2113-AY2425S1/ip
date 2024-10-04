package Ui;

import commands.Task;
import constants.Statements;
import constants.Utils;

import java.util.ArrayList;

public class Ui {
    /**
     * Welcome users to the application through the welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        System.out.println("But first, let me load your previous submissions!");
    }

    /**
     * Print a horizontal line to separate between the user inputs and Cy's output.
     */
    public void printLine() {
        System.out.println(Utils.HORIZONTAL_LINE);
    }

    /**
     * Print mark or unmark statement based on the task's isDone() parameter.
     * Print mark when the task is completed, vice versa.
     */
    public void printMarkOutput(Task task) {
        printLine();

        if (task.isDone()) {
            System.out.println(Statements.CONFIRM_MARK);
        } else {
            System.out.println(Statements.CONFIRM_UNMARK);
        }

        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }

    /**
     * Print todo message with the added todo task
     *
     * @param task todo added to the tasks ArrayList
     * @param items ArrayList that stores the new task
     */
    public void printTodoMessage(String task, ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_ADD);
        printListUpdate(task,items);
        printLine();
    }
    
    /**
     * Print the new task item and the count of task within the list (after adding the item)
     */
    public void printListUpdate(String task, ArrayList<Task> items) {
        System.out.println(task);
        System.out.println("Now you have " + (items.size()) + " tasks in the list");
    }
    /**
     * Print deadline message with the added todo task
     *
     * @param deadline deadline added to the tasks ArrayList
     * @param items ArrayList that stores the new task
     */
    public void printDeadlineMessage(String deadline,ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_DEADLINE);
        printListUpdate(deadline,items);
        printLine();
    }
    /**
     * Print event message with the added the event task
     * @param event event added to tasks ArrayList
     * @param items ArrayList to store the items
     */
    public void printEventMessage(String event, ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_EVENT);
        printListUpdate(event,items);
        printLine();
    }
    /**
     * Print delete message with the deleted task
     * @param deleteItem task to be deleted
     */
    public void printDeleteMessage(Task deleteItem) {
        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
    }
    /**
     * End the application through a goodbye message
     */
    public void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
