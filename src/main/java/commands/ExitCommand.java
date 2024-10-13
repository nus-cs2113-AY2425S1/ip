package commands;

import task.TaskList;
import UI.Ui;
import storage.Storage;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface for interactions.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true; // This is an exit command.
    }
}
