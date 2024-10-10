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

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase(); // Normalize to lowercase for case-insensitive search
    }

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
