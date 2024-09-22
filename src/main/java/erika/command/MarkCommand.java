package erika.command;

import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index){
        this.index = index;
    }
    public void execute(TaskList tasks, FileSystem fileSystem) throws OutOfBoundsException, IOException {
        if (index <= 0 || index > tasks.getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        markEntry(tasks, index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }

    private void markEntry(TaskList tasks, int index) {
        Task task = tasks.getTask(index - 1);
        task.setMark(true);
        Console.printMarkedMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
