package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;

/**
 * Abstract base class representing a command that can be executed.
 * All specific commands (e.g., Add, Delete, Mark, etc.) should extend this class
 * and provide implementation for the {@code execute} method.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasklist The task list on which the command operates.
     * @param ui The UI component to interact with the user.
     * @param storage The storage component to save or load tasks.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception;

    /**
     * Returns whether the command is an exit command that terminates the program.
     *
     * @return {@code true} if the command is an exit command, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
