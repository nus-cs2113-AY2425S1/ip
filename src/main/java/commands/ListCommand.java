package commands;

import task.TaskList;
import UI.Ui;
import storage.Storage;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}

