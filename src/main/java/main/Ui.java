package main;

import task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";
    public static final String INVALID_EVENT_INPUT_MESSAGE = "event <event name> /from <start date/time> /end <end date/time>";
    public static final String INVALID_DEADLINE_INPUT_MESSAGE = "deadline <deadline name> /by <deadline>";
    public static final String INVALID_TODO_INPUT_MESSAGE = "todo <task name>";
    private static final String INVALID_MARK_MESSAGE = "mark <task index>";
    private static final String INVALID_UNMARK_MESSAGE = "unmark <task index>";
    private static final String INVALID_DELETE_MESSAGE = "delete <task index>";

    public static void printHorizontalLine() {
        System.out.println(DRAW_HORIZONTAL_LINE);
    }

    public static void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm");
        System.out.println("\t.___  ___.  _______  __      \n" +
                "\t|   \\/   | |   ____||  |     \n" +
                "\t|  \\  /  | |  |__   |  |     \n" +
                "\t|  |\\/|  | |   __|  |  |     \n" +
                "\t|  |  |  | |  |____ |  `----.\n" +
                "\t|__|  |__| |_______||_______|\n" +
                "\t                             ");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public static void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void printInvalidTaskMessage() {
        System.out.println("\tInvalid command format:" + System.lineSeparator() + "\t\t" + INVALID_TODO_INPUT_MESSAGE
                + System.lineSeparator() + "\t\t" + INVALID_DEADLINE_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_EVENT_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_MARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_UNMARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_DELETE_MESSAGE);
    }

    public static void printTaskDescriptionEmptyMessage() {
        System.out.println("\tError: The task description cannot be empty.");
    }

    public static void printAddedMessage(ArrayList<Task> itemArrayList, Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (itemArrayList.size()) + " tasks in the list.");
    }

    public static void printUnknownErrorMessage() {
        System.out.println("Unknown error experienced.");
    }

    public static void printTaskMarkedMessage(ArrayList<Task> itemArrayList, int itemNum) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    public static void printTaskUnmarkedMessage(ArrayList<Task> itemArrayList, int itemNum) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    public static void printInputIndexOutOfRangeMessage() {
        System.out.println("\tInput index number out of range.");
    }

    public static void printInputIndexNotAnIntegerMessage() {
        System.out.println("\tInput index was not a integer.");
    }

    public static void printTaskDeletedMessage(ArrayList<Task> itemArrayList, Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + itemArrayList.size() + " tasks in the list.");
    }
}
