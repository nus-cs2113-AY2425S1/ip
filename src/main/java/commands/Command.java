package commands;

import java.util.ArrayList;

import exceptions.IllegalCommandException;
import tasks.Task;
import ui.Ui;

public abstract class Command {
    public abstract void execute(ArrayList<Task> taskList, Ui ui) throws IllegalCommandException;
}
