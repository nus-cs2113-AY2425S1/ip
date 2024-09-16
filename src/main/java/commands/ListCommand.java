package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.LIST_COMMAND;

public class ListCommand extends Command {
    public ListCommand() {
        super(LIST_COMMAND);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
