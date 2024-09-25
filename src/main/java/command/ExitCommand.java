package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.MondayException; // Import the exception

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException { // Declare it can throw MondayException
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
