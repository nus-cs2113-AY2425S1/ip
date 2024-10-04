package Ui;

import commands.Task;
import constants.Statements;
import constants.Utils;

import java.util.ArrayList;

public class Ui {

    public void printWelcomeMessage() {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        System.out.println("But first, let me load your previous submissions!");
    }


    public void printLine() {
        System.out.println(Utils.HORIZONTAL_LINE);
    }

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


    public void printTodoMessage(String task, ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_ADD);
        printListUpdate(task,items);
        printLine();
    }

    public void printListUpdate(String task, ArrayList<Task> items) {
        System.out.println(task);
        System.out.println("Now you have " + (items.size()) + " tasks in the list");
    }

    public void printDeadlineMessage(String deadline,ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_DEADLINE);
        printListUpdate(deadline,items);
        printLine();
    }

    public void printEventMessage(String event, ArrayList<Task> items) {
        printLine();
        System.out.println(Statements.CONFIRM_EVENT);
        printListUpdate(event,items);
        printLine();
    }

    public void printDeleteMessage(Task deleteItem) {
        printLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(deleteItem.getStatusIcon() + " " + deleteItem.getDescription());
    }

    public void printEndingMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
