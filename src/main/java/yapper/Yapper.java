package yapper;

import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;
import yapper.instructions.InstructionHandler;
import yapper.io.FileHandler;
import yapper.io.InputFileHandler;
import yapper.io.InputStringHandler;
import yapper.io.OutputFileHandler;
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
     * If save folder and/or save file is missing, this creates them
     * and store all tasks in the task list into the save file.
     *
     * @param taskHandler the {@code TaskHandler} instance that manages the tasks to be stored.
     */
    private static void validateSaveFolderAndFile(TaskHandler taskHandler) {
        try {
            if (!FileHandler.saveFolderExists() || !FileHandler.saveFileExists()) {
                FileHandler.initSaveFileAndFolder(false);
                OutputFileHandler.storeAllTasks(taskHandler);
            }
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }
    }

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
    private static void runMainLoop(TaskHandler taskHandler) {
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
                validateSaveFolderAndFile(taskHandler);
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
        TaskHandler taskHandler = InputFileHandler.loadTasksFromFile();
        if (taskHandler.isEmpty()) {
            System.out.println(StringStorage.START_UP_MESSAGE_IF_SAVE_FILE_NOT_FOUND);
            System.out.println(StringStorage.LINE_DIVIDER);
            System.out.println(StringStorage.HELP_MESSAGE);
        } else {
            System.out.println(StringStorage.START_UP_MESSAGE_IF_SAVE_FILE_FOUND);
        }

        runMainLoop(taskHandler);

        System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
