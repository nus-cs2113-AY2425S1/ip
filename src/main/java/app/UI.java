package app;

import tasks.Task;

import java.util.ArrayList;

import static app.Messages.HELP_MESSAGE;
import static app.Messages.REFRESHER_CLOSING;
import static app.Messages.TERRI_WELCOME_MSG;
import static app.Messages.TERRI_LOGO;

/**
 * The {@code UI} class handles generic text output provided to the user.
 * It contains error and user guidance responses, handles user instruction, and contains standard UI elements,
 * like section dividers.
 */
public class UI {

    /**
     * Prints a welcome message along with the application logo and instructions.
     * This method is called at the start of the application.
     */
    static void printWelcomeMessage() {
        System.out.println(TERRI_LOGO);
        System.out.println(TERRI_WELCOME_MSG);
        printInstructions();
        printDivider();
    }

    /**
     * Prints instructions on how to use the chatbot and the available commands.
     * This method is called at the beginning of the application and when a refresher
     * is requested.
     */
    static void printInstructions() {
        printDivider();
        System.out.println(HELP_MESSAGE);
        printDivider();
        System.out.println(REFRESHER_CLOSING);
    }

    /**
     * Prints a divider line used to visually separate sections of output.
     */
    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    /**
     * Displays the current list of tasks with their status and additional task-type-specific details
     * (e.g., deadlines, event times). The tasks are printed in a numbered list, starting from 1.
     */
    public static void listTasks(ArrayList<Task> tasksStorage, int taskCounter) {
        System.out.println("Here's what you've got in your list:");
        for (int i = 1; i <= taskCounter; i++) {

            // Print task name/completion in generic format
            System.out.print(i + ". " + tasksStorage.get(i - 1).getTypeIcon()
                    + tasksStorage.get(i - 1).getStatusIcon() + tasksStorage.get(i - 1).getTaskName());

            // Then print task-specific information
            System.out.println(tasksStorage.get(i-1).getTaskDetails());
        }
    }

    /**
     * Prints the total number of tasks currently logged in the list, providing user feedback after
     * task-related operations such as addition or deletion.
     */
    public static void printNumberOfTasks(ArrayList<Task> tasksStorage) {
        if (tasksStorage.size() == 1) {
            System.out.println("There is now (1) logged task/event.");
        } else {
            System.out.println("There are now (" + tasksStorage.size() + ") logged tasks/events.");
        }
    }

}
