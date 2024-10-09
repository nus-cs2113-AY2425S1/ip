package bitwise.chat;

import java.io.IOException;

import bitwise.exceptions.*;
import bitwise.tasks.*;
import bitwise.utils.FileManager;
import bitwise.utils.InputHandler;
import bitwise.utils.OutputManager;
import java.util.ArrayList;

/**
 * The {@code Bitwise} class represents the main driver for a task management chatbot.
 * It manages tasks, processes user input, and handles file operations related to task persistence.
 */
public class Bitwise {

    private static ArrayList<Task> tasksList = new ArrayList<Task>();
    private static int numberOfTasks = 0;

    public static void setNumberOfTasks(int numberOfTasks) {
        Bitwise.numberOfTasks = numberOfTasks;
    }
    /**
     * The main method of the application.
     * Loads tasks from file, prints a welcome message, and starts the input manager.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            numberOfTasks = FileManager.getTasks(tasksList);
        } catch (IOException ignored) {
        }
        OutputManager.printWelcomeMessage();
        mainManager();
        OutputManager.printExitMessage();
    }
    /**
     * Manages the chatbot state.
     * Continuously runs until the status is set to {@code Status.EXIT}.
     */
    public static void mainManager() {
        String userInput;
        Status status = Status.RUNNING;
        while (status != Status.EXIT) {
            userInput = InputHandler.getUserInput();
            try {
                status = InputHandler.handleInput(userInput, tasksList, numberOfTasks);
            } catch (InvalidCommandException e) {
                OutputManager.printMessage("Invalid command: " + userInput);
                OutputManager.printListCommands();
            } catch (InvalidFormatException e) {
                OutputManager.printMessage("Invalid format: " + e);
                OutputManager.printLineBreak();
            }
        }
    }
}
