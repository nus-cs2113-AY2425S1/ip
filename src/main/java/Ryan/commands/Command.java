package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.exceptions.RyanException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws RyanException;

    public boolean isExit() {
        return false;
    }
}

