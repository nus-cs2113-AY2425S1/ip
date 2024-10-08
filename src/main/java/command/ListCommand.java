package command;

import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;

/**
 * Represent command to list all tasks in task list.
 */
public class ListCommand extends Command {

    /**
     * Abstract command used to execute ListCommand for displaying all task in task lists.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}