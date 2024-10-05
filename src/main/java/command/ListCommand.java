package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}