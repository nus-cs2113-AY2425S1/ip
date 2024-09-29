package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;

/**
 * Handles the "bye" command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showEndScreen();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
