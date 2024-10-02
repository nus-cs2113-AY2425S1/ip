package freedom.ui;

import java.util.Scanner;

public class Ui {
    private final static String DIVIDER = "\t________________________________________\n";
    private static Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }

    public void printStartMessage() {
        printHeadDivider();
        System.out.print("""
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """);
        printTailDivider();
    }

    public void printEndMessage() {
        printHeadDivider();
        System.out.println("\tBye! Hope to see you again soon.");
        printTailDivider();
    }

    public void printInvalidCommand() {
        printHeadDivider();
        System.out.println("\tSorry! I don't understand your command");
        printTailDivider();
    }

    public void printInvalidDateTime() {
        printHeadDivider();
        System.out.print("""
                \tDate and/or Time is not correctly formatted!
                \tUse this: dd/mm/yyyy hhmm
                \tYou can use placeholders like 0000 if the time is not specified.
                """);
        printTailDivider();
    }

    public void printHeadDivider() {
        System.out.print(DIVIDER);
    }

    public void printTailDivider() {
        System.out.println(DIVIDER);
    }

    public void printPlaceholder() {
        System.out.print("");
    }
}
