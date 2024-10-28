import java.util.ArrayList;

/**
 * Command to find tasks matching a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes the command to find tasks in the task list that match the keyword.
     *
     * @param tasks   The task list to search for matching tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            ui.showMessage("No tasks found with the keyword: " + keyword);
        } else {
            ui.showFoundTasks(foundTasks);
        }
    }
}
