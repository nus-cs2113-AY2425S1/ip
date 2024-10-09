package commands;

import exceptions.IllegalCommandException;
import tasks.TaskList;
import ui.Ui;

/**
 * Command class represents an abstract base class for all commands.
 * Subclasses must implement the {@code execute} method to perform specific actions
 * on the {@code TaskList} and interact with the {@code Ui}.
 */
public abstract class Command {
    /**
     * Executes the command
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IllegalCommandException if the command cannot be executed
     */
    public abstract void execute(TaskList taskList, Ui ui) throws IllegalCommandException;
}
