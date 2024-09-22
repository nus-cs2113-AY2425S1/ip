package erika.command;

import erika.exception.EmptyListException;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.console.Console;
import erika.tasklist.TaskList;

public class ListCommand extends Command{
    public void execute(TaskList tasks, FileSystem fileSystem) throws EmptyListException {
        tasks.printListMessage();
    }
    public boolean isExit() {
        return false;
    }
}
