package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;

import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;
import java.io.IOException;
import ran.exception.MissingArgumentException;

public class Command {
    protected String commandArg;

    public Command(String commandArg) {
        this.commandArg = commandArg;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException, 
           OutOfListBoundsException, EmptyListException, MissingArgumentException {
        return false;
    }
}
