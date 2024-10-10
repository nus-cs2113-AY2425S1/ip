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
 * Initialises the Storage, Ui and CommandHandler.
 * It reads user inputs, parses them, saves data to and from a file.
 * File path is found under constants.Utils.
 */
public class Cy {

    private final Storage storage;
    private final Ui ui;
    private final CommandHandler commandHandler;

    /**
     * Cy constructor.
     */
    public Cy() {
        ui = new Ui();
        storage = new Storage();
        commandHandler = new CommandHandler();
    }

    /**
     * Runs the application.
     *
     * @throws FileNotFoundException when the file could not be found
     * when printing existing file contents.
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