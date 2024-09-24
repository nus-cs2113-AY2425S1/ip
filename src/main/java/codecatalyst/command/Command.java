package codecatalyst.command;

import codecatalyst.Storage;
import codecatalyst.TaskList;
import codecatalyst.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return false;
    }
}
