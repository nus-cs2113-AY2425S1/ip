package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException;

    public boolean isExit() {
        return false;
    }
}
