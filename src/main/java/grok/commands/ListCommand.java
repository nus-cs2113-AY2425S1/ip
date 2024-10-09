package grok.commands;

import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This retrieves and displays the current list of tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     * If the task list is empty a message will be shown to inform the user.
     * @param tasks the TaskList containing the tasks.
     * @param ui the Ui used to display messages to the user.
     * @param storage the Storage used to handle task persistence (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        if (tasks.size() == 0) {
            ui.showMsg("The task list is empty.");
        } else {
            ui.showMsg("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMsg((i + 1) + ". " + tasks.getTask(i));
            }
        }
        ui.showLine();
    }

    /**
     * Indicates that this command does not terminate the program.
     * @return false as the list command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
