package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

public abstract class Command {
    public abstract void execute(TaskList tasks, UserInteraction ui, Storage storage) throws Exception;
    public boolean isExit() {
        return false;
    }
}