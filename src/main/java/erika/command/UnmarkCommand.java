package erika.command;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;

import java.io.IOException;
/**
 * Represents a specific "Unmark" command as interpreted from the user. An <code>Unmark</code> object corresponds
 * to a user command to mark a <code>Task</code> object as not done
 */
public class UnmarkCommand extends Command{
    private int index;
    /**
     * @param index The index of the item the user wishes to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    /**
     * Overrides the default Command method execute
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws OutOfBoundsException, IOException {
        if (index <= 0 || index > tasks.getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        tasks.markEntry(index, false);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }
    /**
     * Checks if the command is an exit command
     * @return <code>false</code> since UnmarkCommands are not ExitCommands
     */
    public boolean isExit() {
        return false;
    }
}
