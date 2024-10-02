import java.util.List;
import java.util.ArrayList;

/**
 * Represents the command to find tasks that match a given keyword.
 * The search is case-insensitive and checks if the keyword is present in the task descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     * Displays all tasks that match the keyword in their descriptions.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The UI object to display results to the user.
     * @param storage The storage object (not used in this command).
     * @throws BebeException if there is an issue with finding tasks (e.g., invalid keyword).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        List<Task> matchingTasks = new ArrayList<>();
        List<Task> allTasks = tasks.getTasks();

        // Find tasks that contain the keyword
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.show("No matching tasks found.");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.show((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
