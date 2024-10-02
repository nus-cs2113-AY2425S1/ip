package commands;

import tasks.Task;
import exceptions.IllegalCommandException;

import java.util.ArrayList;

public class InvalidCommand extends Command {
    @Override
    public void execute(ArrayList<Task> taskList) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
