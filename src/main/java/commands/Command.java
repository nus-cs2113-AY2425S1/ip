package commands;

import exception.BentoException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

public abstract class Command {
    private String commandPrefix;
    protected Parser parser;
    protected boolean isExit = false;

    public Command(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        this.parser = new Parser();
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void saveTask(Storage storage, TaskList tasks, Ui ui) throws SaveFileErrorException {
        storage.saveTaskListQuiet(tasks, ui, parser);
    }

    public void saveTaskList(Storage storage, TaskList tasks, Ui ui) throws SaveFileErrorException {
        storage.saveTaskList(tasks, ui, parser);
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BentoException;
}
