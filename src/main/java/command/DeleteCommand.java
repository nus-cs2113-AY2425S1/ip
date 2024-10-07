package command;

import exception.UserInputException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final int DELETE_COMMAND_PREFIX_LENGTH = 7;

    private String input;

    /**
     * Constructs a DeleteCommand with the specified input.
     *
     * @param input the input string containing the task number to delete
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws UserInputException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UserInputException {
        try {
            String taskNumberStr = input.substring(DELETE_COMMAND_PREFIX_LENGTH).trim(); // Extract the task number part

            if (taskNumberStr.isEmpty()) { // Check if the task number is empty
                throw new UserInputException(" Task number cannot be empty.");
            }

            int taskNumber = Integer.parseInt(taskNumberStr) - 1;

            if (tasks.isValidTaskNumber(taskNumber)) {
                ui.showTaskRemoved(tasks.remove(taskNumber), tasks.size());
                storage.save(tasks.getTasks());
            } else {
                throw new UserInputException(" Invalid task number.");
            }

        } catch (NumberFormatException e) {
            throw new UserInputException(" Task number must be a valid integer.");
        }
    }
}
