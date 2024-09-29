package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Event;
import java.io.IOException;
import ran.exception.MissingArgumentException;

public class AddEventCommand extends Command {
    private final String FROM_PREFIX = "/from";
    private final String TO_PREFIX = "/to";

    public AddEventCommand(String commandArg) {
        super(commandArg);
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgumentException,
           IOException {
        int fromPrefixIndex = commandArg.indexOf(FROM_PREFIX);
        int toPrefixIndex = commandArg.indexOf(TO_PREFIX);
        if (commandArg.equals("") || fromPrefixIndex < 1 || toPrefixIndex < 1 
                || fromPrefixIndex > toPrefixIndex) {
            throw new MissingArgumentException(CommandType.EVENT);
        }
        String description = commandArg.substring(0, fromPrefixIndex - 1);
        String from = commandArg.substring(fromPrefixIndex + FROM_PREFIX.length() + 1, toPrefixIndex - 1);
        String to = commandArg.substring(toPrefixIndex + TO_PREFIX.length() + 1);
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        int taskCount = tasks.getTaskCount();
        storage.addToDataFile(newEvent.dataFileInput());
        ui.printAddedTask(newEvent.toString(), taskCount);
        return false;
    }
}
