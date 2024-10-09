package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

/**
 * Represents a command that is not recognised by ATOM.
 */
public class InvalidCommand extends Command{

    /**
     * Displays an invalid command message to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
