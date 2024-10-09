package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command{

    /**
     * Exits the program and stores the task list contents in a txt file.
     * <p>
     * The relative file path of the txt file is defined in the <code>Storage</code> class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.store(tasks);
        ui.showExitMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }


}
