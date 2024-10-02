package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;

/**
 * Represents a command that can be executed by ChattyCharlie.
 * Implementations of this interface should define specific commands for the task management system.
 */
public interface Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList the list of tasks to be managed.
     * @param ui the user interface to interact with the user.
     * @param storage the storage system to save and load tasks.
     * @throws CharlieExceptions if an error occurs during the execution of the command.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions;

    /**
     * Determines if this command is an exit command.
     * The default implementation returns <code>false</code> as
     * most commands do not cause the application to exit.
     *
     * @return <code>true</code> if the command causes the application to exit, which is only the Bye Command
     * <code>false</code> otherwise.
     */
    default boolean isExit() {
        return false;  // Default behavior: most commands are not exit commands
    }
}
