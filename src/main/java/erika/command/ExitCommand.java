package erika.command;

import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;

/** Represents a command to exit the applicaiton. */
public class ExitCommand extends Command {
    /**
     * Overrides the default Command method execute.
     *
     * @param tasks TaskList object representing the tasks stored.
     * @param fileSystem FileSystem object used to interface with the file system of the host.
     */
    public void execute(TaskList tasks, FileSystem fileSystem) {
        Console.printGoodbyeMessage();
    }
    /**
     * Checks if the command is an exit command.
     *
     * @return <code>true</code> since this is an ExitCommand.
     */
    public boolean isExit() {
        return true;
    }
}
