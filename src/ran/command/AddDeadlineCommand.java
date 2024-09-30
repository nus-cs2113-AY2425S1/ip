package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;
import ran.task.Deadline;
import java.io.IOException;
import ran.exception.MissingArgumentException;

/**
 * Represents a user-prompted command to add a deadline task. A <code>AddDeadlineCommand</code> object 
 * corresponds to a command represented with its argument stored as one string, which is the attribute 
 * of the deadline task being added. This attribute should be formatted in the form of: 
 * "[description of deadline task] /by [time of deadline]".
 * Inherits from base <code>Command</code> class.
 */
public class AddDeadlineCommand extends Command {
    private final String BY_PREFIX = "/by";

    /**
     * Constructor of a <code>AddDeadlineCommand</code> object.
     *
     * @param commandArg String representing attribute of the deadline task being added
     */
    public AddDeadlineCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the command to add a deadline task to a <code>TaskList</code>, 
     * display a message of what has been added, 
     * and update the data file accordingly to be in sync.
     *
     * @param tasks TaskList object representing a list of tasks to be acted upon
     * @param ui Ui object representing the user interface to display the message
     * @param storage Storage object representing the data file to be modified
     * @return Boolean value of false as terminating condition is not met
     * @throws IOException If encounter error interfacing
     * @throws MissingArgumentException If command argument doesn't meet attribute requirement of a deadline
     */
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
