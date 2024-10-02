package commands;

import exceptions.IllegalCommandException;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui) throws IllegalCommandException;
}
