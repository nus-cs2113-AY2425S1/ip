package akshan.util;

import java.util.Scanner;

public final class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints a line break.
     */
    public void printLine() {
        Printer.printLine();
    }

    /**
     * Prints the welcome message and ASCII art upon starting Akshan.
     */
    public void printInitMessage() {
        Printer.printInitMessage();
    }

    /**
     * Prints bye sequence upon exiting Akshan.
     */
    public void printByeMessage() {
        Printer.printByeMessage();
    }

    /**
     * Scans for user input.
     * @return The user input in string representation.
     */
    public String getInput() {
        Printer.printLine();
        String input = this.sc.nextLine();
        Printer.printLine();
        return input;
    }
}
