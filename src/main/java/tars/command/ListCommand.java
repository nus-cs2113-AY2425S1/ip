package tars.command;

import tars.userinterface.UserInterface;
import tars.tasklist.TaskList;
import tars.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }
}
