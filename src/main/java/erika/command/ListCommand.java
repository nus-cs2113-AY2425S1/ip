package erika.command;

import erika.exception.EmptyListException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;


public class ListCommand extends Command{
    /**
     * @param tasks TaskList object representing the tasks stored
     * @param fileSystem FileSystem object used to interface with the file system of the host
     */
    public void execute(TaskList tasks, FileSystem fileSystem) throws EmptyListException {
        tasks.printListMessage();
    }

    /**
     * Checks if the command is an exit command
     * @return <code>false</code> since ListCommands are not ExitCommands
     */
    public boolean isExit() {
        return false;
    }
}
