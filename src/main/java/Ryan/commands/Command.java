package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.exceptions.RyanException;

/**
 * Abstract class representing a command in the task management system.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface for displaying messages.
     * @throws RyanException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws RyanException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return false by default, can be overridden by specific commands.
     */
    public boolean isExit() {
        return false;
    }
}
