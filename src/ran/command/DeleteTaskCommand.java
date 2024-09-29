package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;
import ran.task.Task;
import ran.command.CommandType;
import ran.exception.MissingArgumentException;
import ran.exception.OutOfListBoundsException;
import java.lang.NumberFormatException;
import java.io.IOException;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws OutOfListBoundsException,
           IOException, MissingArgumentException {
        int index;
        try {
            int commandNumber = Integer.parseInt(commandArg);
            index = commandNumber - 1;
        } catch (NumberFormatException e) {
            throw new MissingArgumentException(CommandType.DELETE);
        }
        Task deletedTask = tasks.removeTask(index);
        storage.deleteFromDataFile(deletedTask.dataFileInput());
        ui.printDeletedTask(deletedTask.toString(), tasks.getTaskCount());
        return false;
    }
}
