package ui;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------";
    private static final String WELCOME_MESSAGE = "Hello! I'm Iris!\nWelcome Back!";
    private static final String END_CHAT_MESSAGE = "Bye! Hope to see you again soon!";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public static void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
    public static void showDivider() {
        System.out.println(DIVIDER);
    }
    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DIVIDER);
    }
    public static void showEndChatMessage() {
        System.out.println(END_CHAT_MESSAGE);
    }
}
