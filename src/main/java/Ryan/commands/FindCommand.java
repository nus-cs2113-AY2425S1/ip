package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

import Ryan.tasks.Task;

import Ryan.exceptions.RyanException;
import Ryan.exceptions.EmptyDescriptionException;

import java.util.ArrayList;

/**
 * Command to find tasks based on a description.
 */
public class FindCommand extends Command {

    private static final String NO_TASK_FOUND_MESSAGE = "No tasks found with the given description.";

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
            throw new EmptyDescriptionException();
        }

        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage(NO_TASK_FOUND_MESSAGE);
        } else {
            ui.showFoundTasks(matchingTasks);
        }
    }
}
