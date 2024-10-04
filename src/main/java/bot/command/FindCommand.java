package bot.command;

import bot.Storage;
import bot.Ui;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Command to find tasks that match a given keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword.
     *
     * @param tasks   The list of tasks to search.
     * @param ui      The user interface to display results.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }
}