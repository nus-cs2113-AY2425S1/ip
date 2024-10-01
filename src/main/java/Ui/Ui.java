package Ui;

import TaskList.TaskList;
import commands.Task;
import constants.Statements;
import constants.Utils;
import exceptions.IllegalIndexException;


public class Ui {
    /**
     * Welcome users to the application through the welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        System.out.println("But first, let me load your previous submissions!");
    }

    /**
     * Print a horizontal line to separate between the user inputs and Cy's output.
     */
    public static void printLine() {
        System.out.println(Utils.HORIZONTAL_LINE);
    }

    /**
     * End the application through a goodbye message
     */
    public static void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Print delete message with the deleted task
     * @param deleteItem task to be deleted
     */
    public static void printDeleteMessage(Task deleteItem) {
        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
    }

    /**
     * Print event message with the added the event task
     * @param event event added to tasks ArrayList
     * @param count number of tasks in the list (after adding the event)
     */
    public static void printEventMessage(int count, String event) {
        printLine();
        System.out.println(Statements.CONFIRM_EVENT);
        printListUpdate(count, event);
        printLine();
    }

    /**
     * Print the new task item and the count of task within the list (after adding the item)
     */
    public static void printListUpdate(int count, String task) {
        System.out.println(task);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
    }

    /**
     * Print todo message with the added todo task
     * @param count number of tasks in the list (after adding the todo)
     * @param task todo added to the tasks ArrayList
     */
    public static void printTodoMessage(int count, String task) {
        printLine();
        System.out.println(Statements.CONFIRM_ADD);
        printListUpdate(count, task);
        printLine();
    }

    /**
     * Print the items in the list when the 'list' function is called
     *
     * @param count the number of items in the list
     * @param tasks the ArrayList containing all the saved tasks
     */
    public static void printList(int count, TaskList tasks) throws IllegalIndexException{
        printLine();
        System.out.println(Statements.LIST_TASKS);

       if (count == 0) {
           System.out.println("There are no items in the list");
        } else {
           for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i).getStatusIcon() + " " + tasks.getTask(i).getDescription());
            }
        }
        printLine();
    }

    /**
     * Print deadline message with the added deadline task
     * 
     * @param count number of tasks in the list (after adding the deadline)
     * @param deadline deadline added to the tasks ArrayList
     */
    public static void printDeadlineMessage(int count, String deadline) {
        printLine();
        System.out.println(Statements.CONFIRM_DEADLINE);
        printListUpdate(count, deadline);
        printLine();
    }

    /**
     * Print mark or unmark statement based on the task's isDone() parameter.
     * Print mark when the task is completed, vice versa.
     */
    public static void printMarkOutput(Task task) {
        printLine();

        if (task.isDone()) {
            System.out.println(Statements.CONFIRM_MARK);
        } else {
            System.out.println(Statements.CONFIRM_UNMARK);
        }

        System.out.println(task.getStatusIcon() + " " + task.getDescription());
        printLine();
    }
}
