package bento;

import commands.Command;
import exception.BentoException;
import storage.Storage;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

/**
 * The Bento class represents the main application for chatbot.
 * It handles user interactions, processes commands, and manages the
 * lifecycle of the application.
 */
public class Bento {
    private final Ui ui;
    private final Parser parser;
    private final TaskList tasks;
    private final Storage storage;
    private boolean isExit = false;

    /**
     * Constructs a Bento instance and initializes the user interface,
     * parser, task list, and storage.
     */
    public Bento() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Runs the main loop of Bento. It greets the user,
     * loads existing tasks, and continuously processes user commands
     * until the bye command is given.
     */
    public void run() {
        ui.sayKonichiwa();
        try {
            storage.loadTaskList(tasks, ui, parser);
        } catch (BentoException e) {
            ui.displayErrorMessage(e.getMessage());
        }

        // Main application loop
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = parser.getCommand(userInput, true);
                userCommand.execute(tasks, ui, storage);
                isExit = userCommand.isExit();
            } catch (BentoException e) {
                ui.displayErrorMessage(e.getMessage());
            }
        }
    }
}
