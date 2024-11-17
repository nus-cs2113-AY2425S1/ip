package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks to display.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + "." + tasks.getTask(i));
            }
        }
    }
}