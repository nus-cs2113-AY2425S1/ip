package erika.command;

import erika.console.Console;
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
        deleteTask(tasks, index);
        fileSystem.updateFileSystemWithLocalTasks(tasks);
    }

    private void deleteTask(TaskList tasks, int index) throws OutOfBoundsException {
        if (index <= 0 || index > TaskList.getTaskArraySize()) {
            throw new OutOfBoundsException();
        }
        Console.printDeletedMessage(tasks.getTask(index - 1));
        tasks.deleteTask(index - 1);
        TaskList.decrementTaskArraySize();
    }
    public boolean isExit() {
        return false;
    }
}
