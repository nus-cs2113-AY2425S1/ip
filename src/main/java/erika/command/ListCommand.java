package erika.command;

import erika.console.Console;
import erika.exception.EmptyListException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a specific "List" command as interpreted from the user. A <code>ListCommand</code> object
 * corresponds to a user command to list all <code>Task</code>s in the <code>TaskList</code>.
 */
public class ListCommand extends Command {
    /**
     * Overrides the default Command method execute.
     *
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws EmptyListException {
        if (TaskList.getTaskArraySize() < 1) {
            throw new EmptyListException();
        }
        Console.printMessage(printList(tasks));
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return <code>false</code> since ListCommands are not ExitCommands
     */
    public boolean isExit() {
        return false;
    }

    protected String printList(TaskList tasks) {
        return "Here are the items in your list: \n\t"
                + IntStream.range(0, TaskList.getTaskArraySize())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n\t"));
    }
}
