package aether.command;

import aether.task.Task;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.storage.Storage;
import aether.DukeException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to find tasks containing a specified keyword.
 * <p>
 * This command searches through the task list for tasks that include the given keyword
 * in their descriptions. It performs a case-insensitive search and displays the matching
 * tasks to the user.
 * </p>
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();  // Case-insensitive search
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * and displaying the matching tasks to the user with their original order.
     *
     * @param taskList The {@code TaskList} object containing all current tasks.
     * @param ui       The {@code Ui} object responsible for user interactions.
     * @param storage  The {@code Storage} object responsible for saving and loading tasks.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> tasks = taskList.getTasks();

        // Stream through the tasks, filter those containing the keyword, and map them to strings with their original index.
        List<String> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task.toString())  // Keep original index + task string
                .toList();

        if (matchingTasks.isEmpty()) {
            Ui.response("No matching tasks found.");  // Static call for response
        } else {
            Ui.response("Here are the matching tasks in your list:");  // Static call for response
            matchingTasks.forEach(Ui::newLineResponse);  // Static call for newLineResponse
        }
    }
}
