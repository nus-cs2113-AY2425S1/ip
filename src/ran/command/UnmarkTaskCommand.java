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

public class UnmarkTaskCommand extends Command {
    public UnmarkTaskCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws OutOfListBoundsException,
           IOException, MissingArgumentException {
        int index;
        try {
            int commandNumber = Integer.parseInt(commandArg);
            index = commandNumber - 1;
        } catch (NumberFormatException e) {
            throw new MissingArgumentException(CommandType.UNMARK);
        }
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
