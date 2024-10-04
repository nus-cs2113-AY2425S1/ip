package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

import Ryan.tasks.Task;
import Ryan.tasks.Todo;

import Ryan.exceptions.RyanException;

public class TodoCommand extends Command {
    private final String description;

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
