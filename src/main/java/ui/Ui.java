package ui;

import tasks.*;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String receiveCommand() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hello! I'm Nateh.Nateh\nWhat can I do for you?");
        System.out.println(Skeleton.SKELETON);
        System.out.print(Skeleton.LINE_BREAK);
    }

    public void invalidCommand() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Oops?! I don't know that one >.<");
        System.out.print(Skeleton.LINE_BREAK);
    }

    public void invalidTask(Task task) {
        if (task instanceof Todo) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to be missing a description");
            System.out.println("Format: todo <description>");
            System.out.print((Skeleton.LINE_BREAK));
        } else if (task instanceof Deadlines) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /by <deadline>");
            System.out.print((Skeleton.LINE_BREAK));
        } else if (task instanceof Event) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /from <from> /to <to>");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public void fileError() {
        System.out.print((Skeleton.LINE_BREAK));
        System.out.println("Seems like an error occurred");
        System.out.print((Skeleton.LINE_BREAK));
    }
    public void printMarkError(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/ ");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof NullPointerException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/ ");
            System.out.println("format: mark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public void printUnmarkError(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/ ");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } else if (e instanceof NullPointerException) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/ ");
            System.out.println("format: unmark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public void printDeleteError() {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hmm seems like you tried to delete a task that doesn't exist");
        System.out.print(Skeleton.LINE_BREAK);
    }
    public void printAddMessage(Task task) {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.print("added: ");
        task.print();
        System.out.print(Skeleton.LINE_BREAK);
    }
}
