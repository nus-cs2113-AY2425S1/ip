package bron.command;

import bron.parser.Parser;
import bron.storage.FileStorage;
import bron.task.TaskList;
import bron.ui.TextUI;

/**
 * Manages the main command processing flow for the chatbot.
 * It handles user input, delegates command execution to the CommandHandler, and interacts with the user interface.
 */
public class CommandProcessor {

    private CommandHandler commandHandler;
    private TextUI textUI;
    private FileStorage storage;

    /**
     * Constructs a CommandProcessor object with the given TaskList and FileStorage.
     *
     * @param taskList The TaskList to process commands for.
     * @param storage The FileStorage used for saving and loading tasks.
     */
    public CommandProcessor(TaskList taskList, FileStorage storage) {
        this.commandHandler = new CommandHandler(taskList, storage);
        this.textUI = new TextUI();
        this.storage = storage;
    }

    /**
     * Starts the command processing loop, which continues until the user inputs the "bye" command.
     * It reads user input, parses the command, and delegates execution to the CommandHandler.
     */
    public void start() {
        TextUI.displayIntro();
        boolean isReadingInput = true;

        while (isReadingInput) {
            String line = textUI.readCommand();

            Command command = Parser.parseCommand(line);

            commandHandler.handleCommand(command, line);
            if (command == Command.BYE) {
                isReadingInput = false;
            }
        }
        textUI.close();
    }
}