package CodyChen.Command;
import CodyChen.Task.*;
import CodyChen.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
