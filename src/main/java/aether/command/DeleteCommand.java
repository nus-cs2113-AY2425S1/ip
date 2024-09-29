package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;
import java.io.IOException;

/**
 * Handles deletion of tasks from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String arguments) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("Error: Task number out of range.");
        }
        taskList.deleteTask(taskIndex);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            Ui.response("Error saving tasks: " + e.getMessage());
        }
    }
}
