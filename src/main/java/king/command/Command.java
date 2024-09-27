package king.command;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException;
}
