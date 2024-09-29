package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Deadline;
import java.io.IOException;
import ran.exception.MissingArgumentException;

public class AddDeadlineCommand extends Command {
    private final String BY_PREFIX = "/by";

    public AddDeadlineCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgumentException,
           IOException {
        int byPrefixIndex = commandArg.indexOf(BY_PREFIX);
        if (commandArg.equals("") || byPrefixIndex < 1) {
            throw new MissingArgumentException(CommandType.DEADLINE);
        }
        String description = commandArg.substring(0, byPrefixIndex - 1);
        String by = commandArg.substring(byPrefixIndex + BY_PREFIX.length() + 1);
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        int taskCount = tasks.getTaskCount();
        storage.addToDataFile(newDeadline.dataFileInput());
        ui.printAddedTask(newDeadline.toString(), taskCount);
        return false;
    }
}
