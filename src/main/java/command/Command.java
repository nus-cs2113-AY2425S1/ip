package command;

import data.Storage;
import exceptions.IrisException;
import task.TaskList;

/**
 * Template for different commands
 */
public abstract class Command {
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
    public abstract void execute(TaskList tasks, Storage storage) throws IrisException;
}
