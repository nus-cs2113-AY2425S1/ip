package commands;

import exceptions.IllegalCommandException;
import tasks.TaskList;
import ui.Ui;

/**
 * InvalidCommand Object is responsible for
 * handling illegal commands being input
 */
public class InvalidCommand extends Command {
    private final Runnable uiFunction;

    public InvalidCommand(Runnable uiFunction) {
        this.uiFunction = uiFunction;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws IllegalCommandException {
        throw new IllegalCommandException(uiFunction);
    }
}
