package nus.edu.rizzler.command;

import nus.edu.rizzler.exception.RizzlerException;
import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

import java.io.IOException;

/**
 * Represents a command to exit the application, saving the task list to storage before exiting.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Indicates that this command will cause the application to exit.
     *
     * @return {@code true}, indicating the application should terminate after execution.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command by saving the current task list to storage.
     *
     * @param tasks The task list to be saved.
     * @param userInterface The user interface for displaying messages (not used in this command).
     * @param storage The storage to save the task list to before exiting.
     * @throws RizzlerException If an error occurs while saving the task list to storage.
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        try {
            storage.writeToFile(tasks.toCSVString());
        } catch (IOException e) {
            throw new RizzlerException("Failed to save tasks to Rizzler.data: " + e.getMessage());
        }
    }
}


