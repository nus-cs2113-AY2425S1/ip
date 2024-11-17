package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks in the task list that match the given keyword or keywords.
 */
public class FindCommand extends Command {
    private final String keywords;

    /**
     * Constructs a FindCommand object with the specified keywords.
     * Trims the keywords to remove leading and trailing spaces.
     *
     * @param keywords The keywords used to find matching tasks.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords.trim();
    }

    /**
     * Executes the command by searching the task list for tasks whose descriptions contain the keywords.
     * Displays the matching tasks to the user if found, otherwise, a message is shown.
     *
     * @param tasks   The TaskList containing the tasks to search through.
     * @param ui      The Ui object used to interact with the user.
     * @param storage The Storage object for saving and loading tasks (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTaskList();

        List<Integer> matchingIndices = taskList.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keywords))
                .map(taskList::indexOf)
                .distinct()
                .collect(Collectors.toList());

        if (matchingIndices.isEmpty()) {
            ui.printNoMatchingTasks();
        } else {
            ui.showMatchingTasks(taskList, matchingIndices);
        }
    }

    /**
     * Returns a boolean value to indicate whether this command should exit the program.
     *
     * @return false, as this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
