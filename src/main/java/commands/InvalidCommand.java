package commands;

import java.util.ArrayList;

import exceptions.IllegalCommandException;
import tasks.Task;
import ui.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
