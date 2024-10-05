package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;

import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;
import java.io.IOException;
import ran.exception.MissingArgumentException;

/**
 * Represents a generic user-prompted command. A <code>Command</code> object corresponds to a generic
 * command represented with its argument stored as one string.
 * To be used as base class for specific types of command to inherit from.
 */
public class Command {
    protected String commandArg;

    /**
     * Constructor of <code>Command</code> object.
     *
     * @param commandArg String representing the argument passed to the command
     */
    public Command(String commandArg) {
        this.commandArg = commandArg;
    }

    /**
     * Executes the command using the given command argument.
     *
     * @param tasks TaskList object representing a list of tasks to be acted upon
     * @param ui Ui object representing the user interface for command to execute on
     * @param storage Storage object representing the data file to be potentially modified
     * @return Boolean value of false as terminating condition is not met
     * @throws IOException If subclass encounter error interfacing
     * @throws OutOfListBoundsException If subclass tries to access an invalid index in <code>tasks</code>
     * @throws EmptyListException If subclass tries to display an empty <code>tasks</code>
     * @throws MissingArgumentException If subclass has invalid command argument
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException, 
            OutOfListBoundsException, EmptyListException, MissingArgumentException {
        return false;
    }
}
