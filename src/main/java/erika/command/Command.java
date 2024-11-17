package erika.command;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;
import java.io.IOException;

/**
 * Represents an interpreted Command from the user. A <code>Command</code> object corresponds to a
 * single user-issued command from the terminal.
 */

public abstract class Command {
    /**
     * Executes the required operations to perform the command issued by the user.
     *
     * @param tasks A TaskList object storing the current list of tasks.
     * @param fileSystem The current filesystem for storing data into memory.
     * @throws IOException If errors occur during accessing the filesystem.
     * @throws OutOfBoundsException If operation attempts to access memory locations outside the
     * current size of list.
     */
    public abstract void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException;
    /**
     * Checks if the command is an exit command.
     *
     * @return whether the current command is an ExitCommand.
     */

    public abstract boolean isExit();
}
