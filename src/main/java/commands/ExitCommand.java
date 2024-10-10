package commands;

import task.TaskList;
import UI.Ui;
import storage.Storage;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true; // This is an exit command.
    }
}

