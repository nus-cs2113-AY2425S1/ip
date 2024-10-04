package chattycharlie;

import chattycharlie.commands.*;
import chattycharlie.userinteractions. *;

/**
 * Represents the main chatbot application. A <code>ChattyCharlie</code> object corresponds to
 * a personal task management assistant capable of adding, listing, and managing user tasks.
 */
public class ChattyCharlie {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a <code>ChattyCharlie</code> object and initializes its user interface,
     * storage, and task list. Attempts to load tasks from the specified file path.
     *
     * @param filePath The path of the file where tasks are saved and loaded from.
     */
    public ChattyCharlie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            taskList = new TaskList(storage.load());
        } catch (CharlieExceptions e) {
            ui.displayError("First Time here, Welcome to a life of productivity!");
            taskList = new TaskList();
        }
    }

    /**
     * Starts the chatbot and handles the user interactions using a loop until the user decides to exit.
     * Displays greeting, listens for user inputs, and processes the commands accordingly.
     */

    public void run() { //Echo as a function
        ui.displayGreetings();
        Parser parser = new Parser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String line = ui.getUserInput();
                CommandType commandType = parser.getCommand(line);

                Command command = CommandFactory.createCommand(commandType, line);
                command.execute(taskList, ui, storage);

                isExit = command.isExit();

            } catch (CharlieExceptions e) {
                ui.displayError(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.displayError("Oop, did you make a typo? ");
            }
        }
    }

    public static void main (String[]args){
        new ChattyCharlie("data/tasks.txt").run();
    }
}
