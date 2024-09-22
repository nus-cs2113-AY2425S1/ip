package erika.command;

import erika.console.Console;
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
        unmarkEntry(tasks, index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }

    private void unmarkEntry(TaskList tasks, int index) {
        Task task = tasks.getTask(index - 1);
        task.setMark(false);
        Console.printUnmarkedMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
