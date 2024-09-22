package erika.command.addcommand;

import erika.command.Command;
import erika.console.Console;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.task.Task;
import erika.tasklist.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    protected String description;

    public AddCommand(String description) {
        super();
        this.description = description;
    }

    public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException {

    }

    protected void add(TaskList tasks, Task task) {
        tasks.addTask(task);
        TaskList.incrementTaskArraySize();
        Console.printAddedMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
