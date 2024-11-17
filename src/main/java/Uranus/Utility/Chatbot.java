package Uranus.Utility;

import java.util.Scanner;

/**
 * Represents the Chatbot class that can interacts with the user.
 * The Chatbot is initialized by printing a welcome message, displaying available functions,
 * and allowing the user to manage tasks.
 */
public class Chatbot {
    protected static final Scanner in = new Scanner(System.in);

    /**
     * Constructs a new Chatbot instance.
     * Upon construction, the chatbot prints a welcome message, displays available functions,
     * and starts task management for the user.
     */
    public Chatbot() {
        Ui.printWelcomeMessage();
        Ui.printFunctions();
        TaskList.manageTasks();
    }
}
