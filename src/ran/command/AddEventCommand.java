package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Event;
import java.io.IOException;
import ran.exception.MissingArgumentException;

/**
 * Represents a user-prompted command to add an event task. A <code>AddEventCommand</code> object 
 * corresponds to a command represented with its argument stored as one string, which is the attribute 
 * of the event task being added. This attribute should be formatted in the form of: 
 * "[description of event] /from [event start time] /to [event end time]".
 * Inherits from base <code>Command</code> class.
 */
public class AddEventCommand extends Command {
    private final String FROM_PREFIX = "/from";
    private final String TO_PREFIX = "/to";

    /**
     * Constructor of a <code>AddEventCommand</code> object.
     *
     * @param commandArg String representing attribute of the event task being added
     */
    public AddEventCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the command to add an event task to a <code>TaskList</code>, 
     * display a message of what has been added, 
     * and update the data file accordingly to be in sync.
     *
     * @param tasks TaskList object representing a list of tasks to be acted upon
     * @param ui Ui object representing the user interface to display the message
     * @param storage Storage object representing the data file to be modified
     * @return Boolean value of false as terminating condition is not met
     * @throws IOException If encounter error interfacing
     * @throws MissingArgumentException If command argument doesn't meet attribute requirement of an event
     */
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
               Event newEvent = new Event(description.trim(), from.trim(), to.trim());
               tasks.addTask(newEvent);
               int taskCount = tasks.getTaskCount();
               storage.addToDataFile(newEvent.dataFileInput());
               ui.printAddedTask(newEvent.toString(), taskCount);
               return false;
    }
}
