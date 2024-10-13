package commands;

import task.Task;
import task.TaskList;
import UI.Ui;
import storage.Storage;


/**
 * Command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase(); // Normalize to lowercase for case-insensitive search
    }

    /**
     * Executes the command to find tasks containing the keyword.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface for interactions.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.printTasks(matchingTasks);
    }
}
