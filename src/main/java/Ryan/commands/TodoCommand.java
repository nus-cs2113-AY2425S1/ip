package Ryan.commands;

import Ryan.tasks.Task;
import Ryan.tasks.Todo;
import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.exceptions.RyanException;

/**
 * Command to add a Todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        if (description.trim().isEmpty()) {
            throw new RyanException("Todo task description cannot be empty.");
        }

        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
    }
}
