package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

/**
 * Represents a command that prints out the task list.
 */
public class ListCommand extends Command{

    /**
     * Prints out the task list.
     * <p>
     * If the task list is empty, prints out an error message instead.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasksList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
