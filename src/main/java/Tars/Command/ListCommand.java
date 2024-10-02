package Tars.Command;

import Tars.TaskList;
import Tars.Storage;
import Tars.UserInterface;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }
}
