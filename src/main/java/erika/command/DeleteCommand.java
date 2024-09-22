package erika.command;

import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;

import java.io.IOException;
/**
 * Represents a specific "Delete" command as interpreted from the user. An <code>DeleteCommand</code> object corresponds
 * to a user command to remove a <code>Task</code> object to the list
 */
public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index){
        super();
        this.index = index;
    }
    /**
     * Overrides the default Command method execute
     * Deletes the <Code>Task</Code> object from the <code>TaskList</code> object, as specified by <code>index</code>
     * Regenerates the text document in the host filesystem to keep the filesystem synced with the locally saved
     * <code>TaskList</code> list object
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws OutOfBoundsException, IOException {
        deleteTask(tasks, index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }
    /**
     * Checks if the command is an exit command
     * @return <code>false</code> since DeleteCommands are not ExitCommands
     */

    private void deleteTask(TaskList tasks, int index) throws OutOfBoundsException {
        if (index < 1 || index > TaskList.getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        Console.printDeletedMessage(tasks.getTask(index - 1));
        tasks.deleteTask(index - 1);
    }
    public boolean isExit() {
        return false;
    }
}
