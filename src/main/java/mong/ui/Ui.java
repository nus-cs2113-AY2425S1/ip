package mong.ui;

import mong.exception.IllegalTaskFormatException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static mong.task.TaskList.list;

public class Ui {
    public static final String HORIZONTAL_LINE = "--------------------------------------------------";

    public final int LENGTH_DEADLINE = 8;
    public final int LENGTH_BY = 3;
    public final int LENGTH_TODO = 4;
    public final int LENGTH_EVENT = 5;
    public final int LENGTH_FROM = 4;
    public final int LENGTH_TO = 3;

    private static final String logo = "\n" +
            "\n" +
            "___  ___                  _ \n" +
            "|  \\/  |                 | |\n" +
            "| .  . | ___  _ __   __ _| |\n" +
            "| |\\/| |/ _ \\| '_ \\ / _` | |\n" +
            "| |  | | (_) | | | | (_| |_|\n" +
            "\\_|  |_/\\___/|_| |_|\\__, (_)\n" +
            "                     __/ |  \n" +
            "                    |___/   \n" +
            "\n";

    private final Scanner in;
    private final PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        printHorizontalLine();
        out.println("Hello, I am\n" + logo);
        out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void showExitMessage() {
        printHorizontalLine();
        out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out an indexed list of commands given to Mong starting from 1.
     */
    public void printIndexedList(String input) throws IllegalTaskFormatException {
        if (!input.contentEquals("list")) {
            throw new IllegalTaskFormatException("Task format is incorrect!");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println("MONG my god! You have " + list.size() + " task(s) in the list!");
    }

    public void printItemAddedMessage() {
        out.println("Mong-ed! This item has been added: ");
        out.println(list.get(list.size() - 1));
    }

    public void printItemDeletedMessage() {
        out.println("Mong-?!@# This item has been removed: ");
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
