package apsea.command;

import apsea.exception.ApseaException;
import apsea.task.TaskList;
import apsea.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public Command() {

    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void runCommand(TaskList tasklist, Ui ui) throws ApseaException;
}
