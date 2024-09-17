package Uranus.Utility;

import java.util.Scanner;

public class Chatbot {
    protected static final Scanner in = new Scanner(System.in);
    private static final String SEPARATOR = "_________________________________________________________";
    protected static Functions execute = new Functions();

    // Constructor for chatbot
    public Chatbot() {
        printWelcomeMessage();
        Functions.printFunctions();
        execute.taskmaster();
    }

    // Welcome Screen
    public static void printWelcomeMessage() {
        Functions.print(
                "Hello! I'm Uranus, the only Chatbot you'll ever need.",
                "How can I be of service?"
        );
    }

    // Goodbye Screen
    public static void printByeMessage() {
        Functions.print("Bye. Hope to see you again!");
    }
}
