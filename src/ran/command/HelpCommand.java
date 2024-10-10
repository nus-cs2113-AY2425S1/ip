package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;

/**
 * Represents a command to show help. A <code>HelpCommand</code> object 
 * corresponds to a command represented with its argument stored as one string as 
 * per the base class it inherits from, though this is unused. 
 * Inherits from base <code>Command</code> class.
 */
public class HelpCommand extends Command {
    /**
     * Constructor of a <code>HelpCommand</code> object.
     *
     * @param commandArg String representing command argument, unused
     */
    public HelpCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the help command.
     *
     * @param tasks TaskList object representing a list of tasks, unused
     * @param ui Ui object representing the user interface for command to execute on, unused
     * @param storage Storage object representing a data file, unused
     * @return Boolean value of false is returned to represent terminating condition is not yet met
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelp();
        return false;
    }
}
