package aether.ui;

import java.util.Scanner;

/**
 * Handles user interactions.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    public static void showStartScreen() {
        Display.showStartScreen();
    }

    public static void showEndScreen() {
        Display.showEndScreen();
    }

    public static void response(String message) {
        Display.response(message);
    }

    public static void printSeparator() {
        Display.printSeparator();
    }

    /**
     * Reads input from the user.
     * @return the user's input as a String.
     */
    public static String getInput() {
        return scanner.nextLine();
    }
}
