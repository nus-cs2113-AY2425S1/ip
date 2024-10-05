package app;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input, displays welcome and exit messages,
 * and manages the interface of the JeM chatbot.
 */

public class Ui {

    private final Scanner scanner;

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

        System.out.println(" Hello! I'm JeM, Your e-Assistant Personal To-Do list bot! ");
        System.out.println(" Just type out your tasks you have to complete and I will make a list of them for you.");
        System.out.println();
        System.out.println(" For tasks with no deadline, type todo <task name>");
        System.out.println(" For tasks with deadlines, type 'deadline <task name> /by <date and time>' using '/by' "
                            + "to include the date and time");
        System.out.println(" For events, type event <task name> /from <date and time> /to <date and time>"
                            + "using '/to' and '/from' to specify the duration of the event");
        System.out.println();
        System.out.println(" For all <date and time>, type them as 'dd/MM/yyyy HH:mm' where 'HH:mm' are in 24Hrs");
        System.out.println();
        System.out.println(" To mark a task as completed, type 'mark <task index>' and to unmark, "
                            + "type 'unmark <task index>'");
        System.out.println(" Type 'list' to see the current list of tasks");
        System.out.println(" Type 'delete <task index> to delete the task at the specified index");
        System.out.println(" Type 'clear' to empty your tasklist");
        System.out.println(" Type 'find <keyword>' to find the task with the specified keyword");
        System.out.println();
        System.out.println(" Finally, type 'bye' to end the chat!");

        printBreakLine();
    }
}
