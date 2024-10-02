package commands;

import tasks.Task;
import exceptions.IllegalCommandException;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<Task> taskList) throws IllegalCommandException;
}
