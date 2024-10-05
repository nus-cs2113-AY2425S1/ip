package command;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import model.Task;

import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks that match the keyword.
     *
     * @param tasks   the task list to search within
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks (not used in find command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("    No matching tasks found.");
        } else {
            ui.showMessage("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage("    " + (i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false; // Command does not cause program exit
    }
}
