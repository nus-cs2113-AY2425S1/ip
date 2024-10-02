package nateh.commands;

import nateh.classes.Task;
import nateh.exceptions.IllegalCommandException;

import java.util.ArrayList;

public class InvalidCommand extends Command {
    @Override
    public void execute(ArrayList<Task> taskList) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
