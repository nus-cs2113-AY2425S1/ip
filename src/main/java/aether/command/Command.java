package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;

/**
 * Abstract base class for all command types in the application.
 * <p>
 * Defines the essential structure that all concrete command classes must follow.
 * Each specific command must implement the {@code execute} method to perform its designated action.
 * Additionally, commands can indicate whether they should terminate the application
 * by overriding the {@code isExit} method.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary actions on the task list,
     * interacting with the user interface, and handling storage operations.
     *
     * @param taskList The {@code TaskList} object containing all current tasks.
     * @param ui       The {@code Ui} object responsible for user interactions.
     * @param storage  The {@code Storage} object responsible for saving and loading tasks.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines whether this command should trigger the application to exit.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
