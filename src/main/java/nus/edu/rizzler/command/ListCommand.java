package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = String.format("Here’s the rundown on the tasks so far:%n%s", tasks);
        userInterface.displayMessage(message);
    }
}

