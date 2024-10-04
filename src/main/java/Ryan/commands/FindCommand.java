package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.Task;
import Ryan.exceptions.RyanException;

import java.util.ArrayList;

/**
 * Command to find tasks based on a description.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Constructs a FindCommand with the search description.
     *
     * @param description The search term to find matching tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command to search for tasks containing the description.
     *
     * @param taskList The task list to search through.
     * @param ui The user interface to display the found tasks.
     * @throws RyanException If the description is empty.
     */
    public void execute(TaskList taskList, Ui ui) throws RyanException {
        if (description.trim().isEmpty()) {
            throw new RyanException("Find description cannot be empty.");
        }

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showError("No tasks found with the given description.");
        } else {
            ui.showFoundTasks(matchingTasks);
        }
    }
}
