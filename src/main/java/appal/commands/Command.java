package appal.commands;

import appal.exception.AppalException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;
    private String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException;
}
