import CommandHandler.CommandHandler;
import Storage.Storage;

import Ui.Ui;
import exceptions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cy {

    private final Storage storage;
    private final Ui ui;
    private final CommandHandler commandHandler;

    public Cy() {
        ui = new Ui();
        storage = new Storage();
        commandHandler = new CommandHandler();
    }

    public static void main(String[] args) throws IllegalCommandException, IllegalEmptyException, IllegalTaskException, IllegalKeywordException, IllegalIndexException, FileNotFoundException {
        new Cy().run();
    }

    /**
     * This method runs the application.
     * It would first load existing data from the txt file.
     * Next, it would scan and handle commands until the "bye" command is entered,
     * which would exit this method.
     */
    public void run() throws IllegalCommandException, IllegalEmptyException, IllegalTaskException, IllegalKeywordException, IllegalIndexException, FileNotFoundException {

        ui.printWelcomeMessage();

        storage.printFileContents();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();

            try {
                commandHandler.handleCommand(input, command, splitInputs);
            } catch (IllegalEmptyException | IllegalCommandException | IllegalTaskException | IllegalKeywordException |
                     IllegalIndexException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input = scan.nextLine();
        }

        ui.printEndingMessage();
        scan.close();
    }
}