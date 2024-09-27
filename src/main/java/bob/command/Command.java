package bob.command;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}

