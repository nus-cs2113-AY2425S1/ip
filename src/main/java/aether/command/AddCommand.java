package aether.command;

import aether.storage.Storage;
import aether.task.Task;
import aether.task.Todo;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;
import java.io.IOException;

/**
 * Handles the addition of tasks (e.g., Todo, Deadline, Event).
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(String description, TaskType type) throws DukeException {
        switch (type) {
        case TODO:
            this.task = new Todo(description);
            break;
        // Add handling for Deadline and Event creation here
        default:
            throw new DukeException("Error: Unknown task type.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        try {
            storage.save(taskList.getTasks());
            Ui.response("Added task: " + task);
        } catch (IOException e) {
            Ui.response("Error saving tasks: " + e.getMessage());
        }
    }
}
