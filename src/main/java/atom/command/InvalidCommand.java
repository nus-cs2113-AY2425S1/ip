package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
