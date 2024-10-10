package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;

/**
 * Represents a user-prompted terminating command. A <code>TerminateCommand</code> object 
 * corresponds to a command represented with its argument stored as one string as 
 * per the base class it inherits from, though this is unused. 
 * Inherits from base <code>Command</code> class.
 */
public class TerminateCommand extends Command {
    /**
     * Constructor of a <code>TerminateCommand</code> object.
     *
     * @param commandArg String representing command argument, preferably empty string
     */
    public TerminateCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the terminate command.
     *
     * @param tasks TaskList object representing a list of tasks, unused
     * @param ui Ui object representing the user interface for command to execute on, unused
     * @param storage Storage object representing a data file, unused
     * @return Boolean value of true is returned to represent terminating condition is met
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        return true;
    }
}
