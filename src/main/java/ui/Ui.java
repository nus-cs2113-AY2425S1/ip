package ui;

import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

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

    public void generalError() {
        System.out.print((Skeleton.LINE_BREAK));
        System.out.println("Seems like an error occurred");
        System.out.print((Skeleton.LINE_BREAK));
    }
}
