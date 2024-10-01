package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;
import aether.DukeException;
import java.io.IOException;

/**
 * Handles the deletion of tasks from the task list.
 * <p>
 * This command parses the task number provided by the user, validates it,
 * removes the corresponding task from the task list, and ensures that
 * the updated task list is saved to storage.
 * </p>
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified arguments.
     *
     * @param arguments The argument string containing the task number to delete.
     * @throws DukeException If the provided task number is not a valid integer.
     */
    public DeleteCommand(String arguments) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task number.");
        }
    }

    /**
     * Executes the delete command by removing the specified task from the task list
     * and saving the updated list to storage.
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
        taskList.deleteTask(taskIndex);
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            Ui.response("Error saving tasks: " + e.getMessage());
        }
    }
}
