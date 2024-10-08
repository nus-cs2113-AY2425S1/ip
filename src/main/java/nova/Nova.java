package nova;

import nova.command.ExitCommand;

import java.util.Scanner;

/**
 * The main entry point for the Nova task management program.
 */
public class Nova {

    /**
     * The main method where the program starts.
     */
    public static void main(String[] args) {
        Ui.displayWelcomeMessage();
        TaskList taskManager = new TaskList();
        Parser parser = new Parser(taskManager);
        Storage.createStorage();
        Scanner sc = new Scanner(System.in);

        runCommandLoopUntilExitCommand(sc, parser);
    }

    /**
     * Runs the command loop that continues until the user inputs the exit command.
     *
     * @param sc The {@code Scanner} to receive user input.
     * @param parser The {@code Parser} object to handle and process commands.
     */
    private static void runCommandLoopUntilExitCommand(Scanner sc, Parser parser) {
        while (true) {
            String[] userInput = getUserInput(sc);
            if (userInput[0].equals(ExitCommand.COMMAND_WORD)) {
                ExitCommand.execute();
                break;
            }
            parser.handleInput(userInput);
        }
    }

    /**
     * Retrieves user input from the scanner and splits it into command and arguments.
     *
     * @param sc The {@code Scanner} object for reading user input.
     * @return An array containing the command and its arguments.
     */
    private static String[] getUserInput(Scanner sc) {
        String info = sc.nextLine();
        return info.split(" ", 2); // Split input into command and arguments
    }
}
