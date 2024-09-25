package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

/**
 * The Command class is an abstract base class for all commands in the Aegis application.
 * It defines the structure for executing commands and checking if a command is an exit command.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     * This method must be implemented by all subclasses to define specific command behavior.
     *
     * @param tasks   The TaskList that the command will operate on.
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used for loading and saving tasks.
     * @throws AegisException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException;

    /**
     * Checks whether the command is an exit command.
     * By default, this method returns false, indicating the command does not exit the program.
     * Subclasses should override this method if the command is intended to terminate the program.
     *
     * @return true if the command is an exit command; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
