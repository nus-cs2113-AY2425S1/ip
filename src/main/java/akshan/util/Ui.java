package akshan.util;

import java.util.Scanner;

public final class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void printLine() {
        Printer.printLine();
    }

    public void printInitMessage() {
        Printer.printInitMessage();
    }

    public void printByeMessage() {
        Printer.printByeMessage();
    }

    public String getInput() {
        Printer.printLine();
        String input = this.sc.nextLine();
        Printer.printLine();
        return input;
    }

    public void closeScanner() {
        this.sc.close();
    }
}
