package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;
import ran.task.Task;
import ran.exception.OutOfListBoundsException;
import java.io.IOException;

public class UnmarkTaskCommand extends Command {
    public UnmarkTaskCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws OutOfListBoundsException,
           IOException {
        int index = Integer.parseInt(commandArg) - 1;
        Task targetTask = tasks.getTask(index);
        String oldLine = targetTask.dataFileInput();
        targetTask.setAsUndone();
        String newLine = targetTask.dataFileInput();
        storage.modifyDataFile(oldLine, newLine);
        String unmarkedTask = targetTask.toString();
        ui.printUnmarkedTask(unmarkedTask);
        return false;
    }
}
