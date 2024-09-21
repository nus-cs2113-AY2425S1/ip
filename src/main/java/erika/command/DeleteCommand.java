package erika.command;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index){
        super();
        this.index = index;
    }
    public void execute(TaskList tasks, FileSystem fileSystem) throws OutOfBoundsException, IOException {
        tasks.deleteTask(index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }
    public boolean isExit() {
        return false;
    }
}
