package erika.command;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.ui.Ui;
import erika.tasklist.TaskList;

import java.io.IOException;

public class UnmarkCommand extends Command{
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, FileSystem fileSystem) throws OutOfBoundsException, IOException {
        if (index <= 0 || index > tasks.getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        tasks.markEntry(index, false);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }
    public boolean isExit() {
        return false;
    }
}
