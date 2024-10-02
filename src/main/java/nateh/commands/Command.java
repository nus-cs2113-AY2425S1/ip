package nateh.commands;

import nateh.classes.Task;
import nateh.exceptions.IllegalCommandException;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<Task> taskList) throws IllegalCommandException;
}
