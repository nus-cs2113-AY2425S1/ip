package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents an invalid command in the task management system.
 * This command is executed when the user inputs an unrecognized command.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command, informing the user that the command is not recognized.
     *
     * @param tasks the TaskList (not used in this command)
     * @param ui the user interface for displaying messages
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMsg("Sorry. I don't recognize that command.");
    }
}
