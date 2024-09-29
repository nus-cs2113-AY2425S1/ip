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

public class MarkTaskCommand extends Command {
    public MarkTaskCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws OutOfListBoundsException,
           IOException, MissingArgumentException {
        int index;
        try {
            int commandNumber = Integer.parseInt(commandArg);
            index = commandNumber - 1;
        } catch (NumberFormatException e) {
            throw new MissingArgumentException(CommandType.MARK);
        }
        Task targetTask = tasks.getTask(index);
        String oldLine = targetTask.dataFileInput();
        targetTask.setAsDone();
        String newLine = targetTask.dataFileInput();
        storage.modifyDataFile(oldLine, newLine);
        String markedTask = targetTask.toString();
        ui.printMarkedTask(markedTask);
        return false;
    }
}
