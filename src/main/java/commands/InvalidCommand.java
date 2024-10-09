package commands;

import exceptions.IllegalCommandException;
import tasks.TaskList;
import ui.Ui;

/**
 * InvalidCommand Object is responsible for
 * handling illegal commands being input
 */
public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
