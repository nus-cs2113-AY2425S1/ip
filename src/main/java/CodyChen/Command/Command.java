package CodyChen.Command;
import CodyChen.Ui;
import CodyChen.Storage;
import CodyChen.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
