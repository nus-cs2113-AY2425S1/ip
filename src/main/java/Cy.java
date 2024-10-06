import commandhandler.CommandHandler;
import storage.Storage;

import ui.Ui;
import exceptions.IllegalEmptyException;
import exceptions.IllegalCommandException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import exceptions.IllegalIndexException;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main class for the Cy chatbot application!
 * The class initialises the Storage, Ui and CommandHandler.
 * It reads user inputs, parses them, saves data to and from a file.
 */
public class Cy {

    private final Storage storage;
    private final Ui ui;
    private final CommandHandler commandHandler;

    /**
     * Cy constructor
     */
    public Cy() {
        ui = new Ui();
        storage = new Storage();
        commandHandler = new CommandHandler();
    }

    /**
     * This method runs the application.
     * It would first load existing data from the txt file.
     * Next, it would scan and handle commands until the "bye" command is entered,
     * which would exit this method.
     */
    public void run() throws FileNotFoundException {

        ui.printWelcomeMessage();

        storage.printFileContents();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();

            try {
                commandHandler.handleCommand(input, command, splitInputs);
            } catch (IllegalEmptyException | IllegalCommandException | IllegalTaskException |
                     IllegalKeywordException | IllegalIndexException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input = scan.nextLine();
        }

        ui.printEndingMessage();
        scan.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Cy().run();
    }

}