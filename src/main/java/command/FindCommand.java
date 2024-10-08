package command;

import datahandling.Storage;
import task.Task;
import task.TaskList;
import ui.UserInteraction;

import java.util.List;

/**
 * Represent command used to find task containing specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs FindCommand with defined keyword.
     * @param keyword keyword used to search for in task description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Abstract command used to execute FindCommand for searching of task via keyword defined.
     * @param tasks list of task which command takes from
     * @param ui user interaction object for displaying messages
     * @param storage storage handler for saving and loading task
     */
    @Override
    public void execute(TaskList tasks, UserInteraction ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .toList();

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks matching \"" + keyword + "\" found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}