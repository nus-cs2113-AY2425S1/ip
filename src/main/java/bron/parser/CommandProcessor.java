package bron.parser;

import bron.command.Command;
import bron.command.CommandHandler;
import bron.storage.FileStorage;
import bron.task.TaskList;
import bron.ui.TextUI;


public class CommandProcessor {

    private CommandHandler commandHandler;
    private TextUI textUI;
    private FileStorage storage;

    public CommandProcessor(TaskList taskList, FileStorage storage) {
        this.commandHandler = new CommandHandler(taskList, storage);
        this.textUI = new TextUI();
        this.storage = storage;
    }

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