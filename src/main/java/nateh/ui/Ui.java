package nateh.ui;

import java.util.Scanner;

import nateh.tasks.*;

/**
 * The Ui class is responsible for handling user interaction,
 * receiving input, and printing messages to the console.
 * It provides methods for printing specific messages and handling various errors.
 */
public class Ui {
    /**
     * Scanner object for reading user input.
     */
    private final Scanner scanner;
    /**
     * Constructs a Ui object and initializes the Scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Receives a command from the user by reading the next line of input.
     *
     * @return the user command as a String
     */
    public String receiveCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hello! I'm Nateh\nWhat can I do for you?");
        System.out.println(Skeleton.SKELETON);
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints an error message when the user enters an invalid command.
     */
    public void printInvalidCommandError() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Oops?! I don't know that one >.<");
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints an error message when the user enters an invalid task.
     * @param type the invalid Task object that caused the error
     */
    public void printInvalidTaskError(TaskEnum type) {
        switch (type) {
        case TODO -> {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to be missing a description");
            System.out.println("Format: todo <description>");
            System.out.print((Skeleton.LINE_BREAK));
        } case DEADLINE -> {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /by <deadline>");
            System.out.print((Skeleton.LINE_BREAK));
        } case EVENT -> {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: event <description> /from <from> /to <to>");
            System.out.print((Skeleton.LINE_BREAK));
        } default -> {
            return;
        }
        }
    }

    /**
     * Prints a generic error message when a file error occurs.
     */
    public void printFileError() {
        System.out.print((Skeleton.LINE_BREAK));
        System.out.println("Seems like a I/O error occurred. Please restart the program.");
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints an error message when an exception occurs during the marking of a task.
     *
     * @param e the Exception object that caused the error
     */
    public void printMarkError(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof NullPointerException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/");
            System.out.println("format: mark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }

    /**
     * Prints an error message when an exception occurs during the unmarking of a task.
     *
     * @param e the Exception object that caused the error
     */
    public void printUnmarkError(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof NullPointerException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/");
            System.out.println("format: unmark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }

    /**
     * Prints an error message when an attempt to delete a task fails.
     */
    public void printDeleteError() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hmm seems like you tried to delete a task that doesn't exist");
        System.out.println("format: delete <number of the task>");
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task the Task object that was added
     */
    public void printAddMessage(Task task) {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.print("added: ");
        task.print();
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints the error message when the user inputs a date that is formatted incorrectly
     */
    public void printDateError() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("I think you formatted the date wrong =.=");
        System.out.println("Format: yyyy-mm-dd");
        System.out.print(Skeleton.LINE_BREAK);
    }

    /**
     * Prints the error message when the user inputs a search command incorrectly
     */
    public void printSearchError() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("I don't think you can search like that....");
        System.out.println("Searching for tasks with specific date: search /d <date>");
        System.out.println("Searching for tasks before: search /b <date>");
        System.out.println("Searching for tasks after: search /a <date>");
        System.out.println("Searching for tasks containing the term: search /c <search term>");
        System.out.print(Skeleton.LINE_BREAK);
    }
}

