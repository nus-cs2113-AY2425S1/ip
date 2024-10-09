package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

/**
 * Represents an abstract class defining abstract methods for all ATOM commands.
 */
public abstract class Command {

    /**
     * Performs the corresponding operation depending on the command parsed by <code>Parser</code> class.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns the exit status of the program.
     *
     * @return <code>true</code> for exit command, <code>false</code> otherwise.
     */
    public abstract boolean isExit();
}
