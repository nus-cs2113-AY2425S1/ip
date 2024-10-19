package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command by displaying the list of all tasks.
     *
     * @param tasks The task list to display.
     * @param userInterface The user interface for displaying the task list.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = String.format("Here’s the rundown on the tasks so far:%n%s", tasks);
        userInterface.displayMessage(message);
    }
}

