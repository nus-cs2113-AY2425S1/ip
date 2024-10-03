package tars.command;

import tars.tasklist.TaskList;
import tars.storage.Storage;
import tars.userinterface.UserInterface;
import tars.tarsexception.tarsException;

/**
 * Abstract class representing a command.
 * All specific commands must extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     * This method must be implemented by any subclass.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save or load tasks from.
     * @throws tarsException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws tarsException;

    /**
     * Determines if the command is an exit command.
     * By default, this returns false, meaning the command will not exit the program.
     *
     * @return False, indicating that the program will not exit after this command.
     */
    public boolean isExit() {
        return false;
    }
}
