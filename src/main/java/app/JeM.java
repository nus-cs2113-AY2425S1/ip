package app;

import commands.Command;
import taskmanager.taskManager;
import exceptions.InvalidCommandException;

/**
 * JeM is the main class for the JeM chatbot application.
 * It initializes the necessary components (Ui, Storage, and Parser)
 * and handles the main loop of the application, processing user inputs
 * until the chatbot is instructed to exit.
 */

public class JeM {

    private final Ui ui;
    private final taskManager storage;
    private final Parser parser;

    public JeM(){
        ui = new Ui();
        storage = new taskManager();
        parser = new Parser();
    }

    /**
     * Runs the chatbot, accepting user input and processing commands
     * until the user exits by typing 'bye'.
     */

    public void run() {
        ui.displayWelcomeMessage();

        while (true) {
            String userInput = ui.readInput();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.exitChatBot();
                break;
            }
            try {
                Command command = parser.parse(userInput);
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
