package bron;

import bron.command.CommandProcessor;
import bron.storage.FileStorage;
import bron.task.TaskList;
import bron.ui.TextUI;

/**
 * The entry point for the Bron chatbot application.
 * Initializes the storage, task list, and command processor, then starts the chatbot.
 */
public class Bron {
    private TextUI ui;
    private TaskList tasks;
    private FileStorage storage;

    /**
     * The main method that serves as the entry point for the Bron chatbot.
     * Loads tasks from storage, initializes the command processor, and starts the chatbot.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        FileStorage storage = new FileStorage();
        TaskList taskList = storage.load();

        CommandProcessor commandProcessor = new CommandProcessor(taskList, storage);
        commandProcessor.start();
    }
}
