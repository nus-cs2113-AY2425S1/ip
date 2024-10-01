package Ui;

import TaskList.TaskList;
import commands.Task;
import constants.Statements;
import constants.Utils;
import exceptions.IllegalIndexException;


public class Ui {

    public void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        System.out.println("But first, let me load your previous submissions!");
    }

    public static void printLine() {
        System.out.println(Utils.HORIZONTAL_LINE);
    }

    public static void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printDeleteMessage(Task deleteItem) {
        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
    }

    public static void printEventMessage(int count, String event) {
        printLine();
        System.out.println(Statements.CONFIRM_EVENT);
        printListUpdate(count, event);
        printLine();
    }

    public static void printListUpdate(int count, String task) {
        System.out.println(task);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
    }

    public static void printTodoMessage(int count, String task) {
        printLine();
        System.out.println(Statements.CONFIRM_ADD);
        printListUpdate(count, task);
        printLine();
    }

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

    public static void printDeadlineMessage(int count, String deadline) {
        printLine();
        System.out.println(Statements.CONFIRM_DEADLINE);
        printListUpdate(count, deadline);
        printLine();
    }

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
