package ryan.commands;

import ryan.tasks.Task;
import ryan.tasks.Todo;

import ryan.utility.TaskList;
import ryan.utility.Ui;

import ryan.exceptions.RyanException;
import ryan.exceptions.EmptyDescriptionException;

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
            throw new EmptyDescriptionException();
        }

        Task task = new Todo(description);
        tasks.addTask(task);
        Ui.showTaskAdded(task, tasks.size());
    }
}
