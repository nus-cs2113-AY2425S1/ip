package command;

import model.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.MondayException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException;

    public boolean isExit() {
        return false;
    }
}
