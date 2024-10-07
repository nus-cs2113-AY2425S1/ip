package yapper;

import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;
import yapper.instructions.InstructionHandler;
import yapper.io.InputFileHandler;
import yapper.io.InputStringHandler;
import yapper.io.StringStorage;
import yapper.tasks.TaskHandler;

/**
 * Main class for Yapper.
 *
 * <p>
 * The Yapper class implements a simple command-line chatbot.
 * It interacts with the user through a loop that
 * processes user input, handles tasks, and manages program flow.
 * <p/>
 *
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
    public static void runMainLoop(TaskHandler taskHandler) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(StringStorage.LINE_DIVIDER_INPUT);
            String userInputString = scanner.nextLine().trim();

            System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
            if (userInputString.equals(StringStorage.PREFIX_BYE_INSTRUCTION)) {
                break;
            } else if (userInputString.startsWith(StringStorage.PREFIX_BYE_INSTRUCTION)) {
                System.out.println(StringStorage.PREFIX_BYE_INSTRUCTION
                    + " does not need other parameters. ");
                continue;
            }

            try {
                Instruction instruction = InputStringHandler.parseUserInput(userInputString);
                InstructionHandler.handleInstruction(taskHandler, instruction);
            } catch (YapperException e) {
                System.out.println("YapperException has occurred " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The main method that serves as the entry point of the Yapper program.
     *
     * <p>
     * It initializes the TaskHandler, starts the chatbot loop,
     * and displays messages when the program starts and ends.
     * <p/>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        TaskHandler taskHandler = InputFileHandler.loadTasks();
        if (taskHandler.isEmpty()) {
            System.out.println(StringStorage.START_UP_MESSAGE);
            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.HELP_MESSAGE);
        } else {
            System.out.println("let us resume where we left off, shall we?");
        }

        runMainLoop(taskHandler);

        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
