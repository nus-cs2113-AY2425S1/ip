package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The Command class is an abstract base class for all user commands.
 * Each specific command (e.g., AddCommand, DeleteCommand) will inherit from this class and implement the execute method.
 */
public abstract class Command {
    /**
     * Executes the command. This method must be implemented by all subclasses.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The Ui object for user interaction, to display messages or gather input.
     * @param storage The Storage object for saving or loading tasks from a file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether the command should terminate the application.
     * This can be overridden by subclasses if the command (like ByeCommand) exits the program.
     *
     * @return A boolean value indicating whether the command exits the application. Default is false.
     */
    public boolean isExit() {
        return false;
    }
}
