package ran.command;

import main.java.Ui;
import main.java.TaskList;
import main.java.Storage;
import ran.exception.EmptyListException;

/**
 * Represents a user-prompted command to list out and display an <code>TaskList</code> object. 
 * A <code>ShowListCommand</code> object corresponds to a command represented with its argument 
 * stored as one string, as per base class it inherits from, though this is unused.
 * Inherits from base <code>Command</code> class.
 */
public class ShowListCommand extends Command {
    /**
     * Constructor of a <code>ShowListCommand</code> object.
     *
     * @param commandArg String representing command argument, preferably empty string
     */
    public ShowListCommand(String commandArg) {
        super(commandArg);
    }

    /**
     * Executes the command to display a <code>TaskList</code> object as a list.
     *
     * @param tasks TaskList object representing a list of tasks to be displayed
     * @param ui Ui object representing the user interface for command to display on
     * @param storage Storage object representing a data file, unused
     * @return Boolean value of false as terminating condition is not met
     * @throws EmptyListException If <code>TaskList</code> passed in is empty
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException {
        int taskCount = tasks.getTaskCount(); 
        if (taskCount == 0) {
            throw new EmptyListException();
        }
        ui.printList(tasks.getAllTasks(), taskCount);
        return false;
    }
}
