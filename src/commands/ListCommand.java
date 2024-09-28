package commands;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The ListCommand class handles the "list" command, which shows all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by calling the UI to display all tasks in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskListed(tasks);
    }
}

