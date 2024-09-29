package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;
import ran.task.Task;
import ran.exception.OutOfListBoundsException;
import java.io.IOException;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws OutOfListBoundsException,
           IOException {
        int index = Integer.parseInt(commandArg) - 1;
        Task deletedTask = tasks.removeTask(index);
        storage.deleteFromDataFile(deletedTask.dataFileInput());
        ui.printDeletedTask(deletedTask.toString(), tasks.getTaskCount());
        return false;
    }
}
