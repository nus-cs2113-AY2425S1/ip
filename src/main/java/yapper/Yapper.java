package yapper;

import java.util.Scanner;

import yapper.instructions.InstructionHandler;
import yapper.io.OutputFileHandler;
import yapper.io.StringStorage;
import yapper.tasks.TaskHandler;

/**
 * Main class for Yapper.
 * <p>
 * The Yapper class implements a simple command-line chatbot.
 * It interacts with the user through a loop that:
 * processes user input, handles tasks, and manages program flow.
 * <p/>
 */
public class Yapper {

    /**
     * Contains the main Chatbot Loop
     *
     * <p>
     * Starts the main chatbot loop, allowing the user to interact
     * with the chatbot until they decide to exit.
     * <p/>
     *
     * @param taskHandler the TaskHandler instance that manages tasks
     */
    public static void startYappin(TaskHandler taskHandler) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(StringStorage.LINE_DIVIDER_INPUT);
            String userInputString = scanner.nextLine();
            System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
            if (userInputString.trim().equals(
                    StringStorage.BYE_INSTRUCTION_PREFIX)) {
                break;
            } else if (userInputString.trim().startsWith(
                    StringStorage.BYE_INSTRUCTION_PREFIX)) {
                StringStorage.printWithDividers(
                    StringStorage.BYE_INSTRUCTION_PREFIX
                    + " does not need other parameters");
                continue;
            }
            InstructionHandler.handleInstruction(taskHandler, userInputString);
        }
        scanner.close();
    }

    /**
     * The main method that serves as the entry point of the Yapper program.
     *
     * <p>
     * It initializes the TaskHandler.
     * It displays the startup message if no tasks are present in the file.
     * It starts the chatbot loop.
     * It displays a shutdown message when the program ends.
     * <p/>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Pre-Program Message
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        TaskHandler taskHandler = OutputFileHandler.loadTasks();
        if (taskHandler.getCurrTaskTotal() == 0) {
            System.out.println(StringStorage.START_UP_MESSAGE);
            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.HELP_MESSAGE);
        } else {
            System.out.println("let us resume where we left off, shall we?");
        }

        // Startup ChatBot Program
        startYappin(taskHandler);

        // Post-Program Message
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
