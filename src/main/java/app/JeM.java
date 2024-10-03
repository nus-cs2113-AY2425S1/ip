package app;

import Commands.Command;
import taskmanager.Storage;
import exceptions.InvalidCommandException;

/**
 * JeM is the main class for the JeM chatbot application.
 * It initializes the necessary components (Ui, Storage, and Parser)
 * and handles the main loop of the application, processing user inputs
 * until the chatbot is instructed to exit.
 */

public class JeM {

    private Ui ui;
    private Storage storage;
    private Parser parser;

    public JeM(){
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public void run() {
        ui.displayWelcomeMessage();

        while (true) {
            String userInput = ui.readInput();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.exitChatBot();
                break;
            }
            try {
                Command command = parser.parse(userInput, storage);
                command.execute(storage);
            }catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method that serves as the entry point for the JeM application.
     * It creates a new instance of JeM and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        new JeM().run();
    }
}