package app;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input, displays welcome and exit messages,
 * and manages the interface of the JeM chatbot.
 */

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);  // Set up the scanner to read user input
    }

    public String readInput() {
        return scanner.nextLine().trim();  // Read and return user input
    }

    public void printBreakLine() {
        System.out.println("____________________________________________________________");
    }

    public void exitChatBot() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Displays the welcome message and instructions to the user when the chatbot starts.
     * Includes the JeM logo and basic command descriptions.
     */

    public void displayWelcomeMessage() {
        String logo = "      _         __  __ \n"
                + "     | |       |  \\/  |\n"
                + "     | |  ___  | |\\/| |\n"
                + " _   | | / _ \\ | |  | |\n"
                + "| |__| ||  __/ | |  | |\n"
                + " \\____/  \\___| |_|  |_|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();

        System.out.println(" Hello! I'm JeM, Your e-Assistant");
        System.out.println(" Personal To-Do list bot! ");
        System.out.println(" Just type out your tasks you have to complete and I will make a list of them for you.");
        System.out.println(" For tasks with no deadline, type todo <task name>, for tasks with deadlines, type deadline <task name> /by <date and time>");
        System.out.println(" For events, type event <task name> /from <date and time> /to <date and time>");
        System.out.println(" Type 'list' to see the current list of tasks,'delete <task number>' to delete that task");
        System.out.println(" Finally, type 'bye' to end the chat!");

        printBreakLine();
    }
}
