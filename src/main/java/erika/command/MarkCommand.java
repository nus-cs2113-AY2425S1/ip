package erika.command;

import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;
/**
 * Represents a specific "Mark" command as interpreted from the user. An <code>Mark</code> object corresponds
 * to a user command to mark a <code>Task</code> object as done
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * @param index The index of the item the user wishes to mark
     */
    public MarkCommand(int index){
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
        markEntry(tasks, index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }
    /**
     * Checks if the command is an exit command
     * @return <code>false</code> since MarkCommands are not ExitCommands
     */

    private void markEntry(TaskList tasks, int index) {
        Task task = tasks.getTask(index - 1);
        task.setMark(true);
        Console.printMarkedMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
