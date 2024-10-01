package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;

import java.io.IOException;

/**
 * Handles the "mark" and "unmark" commands.
 * <p>
 * This command updates the completion status of a specified task in the task list.
 * It marks a task as done or not done based on the user's input and ensures that
 * the updated task list is saved to storage.
 * </p>
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark;

    /**
     * Constructs a {@code MarkCommand} with the specified task number and mark status.
     *
     * @param arguments The argument string containing the task number to mark/unmark.
     * @param isMark    {@code true} to mark the task as done, {@code false} to unmark it.
     * @throws DukeException If the provided task number is not a valid integer.
     */
    public MarkCommand(String arguments, boolean isMark) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
            this.isMark = isMark;
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task number.");
        }
    }

    /**
     * Executes the mark or unmark command by updating the task's completion status
     * and saving the updated task list.
     *
     * @param taskList The {@code TaskList} object containing all current tasks.
     * @param ui       The {@code Ui} object responsible for user interactions.
     * @param storage  The {@code Storage} object responsible for saving and loading tasks.
     * @throws DukeException If the task number is out of range or an error occurs during execution.
     */
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
