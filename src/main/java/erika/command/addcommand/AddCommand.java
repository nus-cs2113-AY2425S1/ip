package erika.command.addcommand;

import erika.command.Command;
import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;

/**
 * Represents a specific "Add" command as interpreted from the user. An <code>AddCommand</code> object corresponds to
 * a user command to append a <code>Task</code> object to the list
 */

public class AddCommand extends Command {
    protected String description;

    /**
     * @param description Textual description of the entry
     */
    public AddCommand(String description) {
        super();
        this.description = description;
    }
    /**
     * Overrides the default Command method execute
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException {

    }
    /**
     * Executes the required operations to perform the command issued by the user
     * @return <code>false</code> since AddCommands are not ExitCommands
     */

    protected void add(TaskList tasks, Task task) {
        tasks.addTask(task);
        Console.printAddedMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
