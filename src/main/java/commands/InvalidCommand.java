package commands;

import exceptions.IllegalCommandException;
import tasks.TaskList;
import ui.Ui;

/**
 * InvalidCommand Object is responsible for
 * handling illegal commands being input
 */
public class InvalidCommand extends Command {
    /**
     * {@code uiFunction} is a function from the Ui class that
     * gives a response to the user when an invalid command is given
     */
    private final Runnable uiFunction;

    public InvalidCommand(Runnable uiFunction) {
        this.uiFunction = uiFunction;
    }

    /**
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     * @throws IllegalCommandException when an invalid command input is given by the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws IllegalCommandException {
        throw new IllegalCommandException(uiFunction);
    }
}
