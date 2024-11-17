package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;
import aegis.task.Task;

import java.util.ArrayList;

/**
 * The FindCommand class handles searching for tasks in the task list that match a specified keyword.
 * It displays the matching tasks to the user or an error message if no matches are found.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param keyword The keyword used to find matching tasks in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the FindCommand by searching the task list for tasks that contain the keyword.
     * If matching tasks are found, they are displayed to the user; otherwise, an error message is shown.
     *
     * @param tasks   The TaskList to search within.
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used for task persistence (not affected by this command).
     * @throws AegisException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found.");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, indicating the application should not terminate after this command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
