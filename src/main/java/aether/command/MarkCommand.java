package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;

import java.io.IOException;

/**
 * Handles the "mark" and "unmark" commands.
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark;

    public MarkCommand(String arguments, boolean isMark) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
            this.isMark = isMark;
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("Error: Task number out of range.");
        }

        // Mark the task as done or not done
        taskList.markTaskStatus(taskIndex, isMark);

        // Try to save the tasks and handle any IOExceptions
        try {
            storage.save(taskList.getTasks());
            String message = isMark ? "Marked task as done." : "Marked task as not done.";
            ui.response(message);
        } catch (IOException e) {
            // Handle IOException by notifying the user
            ui.response("Error saving tasks: " + e.getMessage());
        }
    }
}
