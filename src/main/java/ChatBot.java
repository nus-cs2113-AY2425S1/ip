import esme.storage.Storage;
import esme.ui.Ui;
import esme.command.CommandManager;

import java.io.IOException;
import java.util.Scanner;

public class ChatBot {
    private final Scanner inputScanner;
    private final Ui ui;
    private final CommandManager commandManager;
    private Storage storage;

    public ChatBot(String filePath) {
        this.inputScanner = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.commandManager = new CommandManager(this.ui, this.storage);
    }

    /**
     * Initializes the application by attempting to load content from the storage
     * file.
     * If the file does not exist, it will be created.
     * If the creation of the file fails (e.g. due to lack of permissions), prints
     * an error message and exits the application.
     * After successful initialization, prints a greeting message to the user.
     */
    public void bootUp() {
        try {
            if (!commandManager.hasLoadSuccessful()) {
                storage.createFile();
            }
        } catch (IOException e) {
            ui.printCreateFileError();
            System.exit(1);
        }
        ui.greet();
    }

    /**
     * The main loop of the application.
     *
     * This method will continue to loop until the user chooses to exit the
     * application. It will read the user's input, process it, and then
     * output the result to the user.
     */
    public void start() {
        bootUp();
        String line;
        boolean toExit = false;

        while (!toExit) {
            line = inputScanner.nextLine();
            toExit = commandManager.handleCommand(line);
        }
        inputScanner.close();
    }

    /**
     * The main entry point of the application.
     *
     * This creates an instance of the ChatBot and starts the user interface loop.
     */
    public static void main(String[] args) {
        ChatBot esme = new ChatBot("tasklist.txt");
        esme.start();
    }
}
