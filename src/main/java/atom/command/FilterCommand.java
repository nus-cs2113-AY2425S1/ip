package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

public class FilterCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
