package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
