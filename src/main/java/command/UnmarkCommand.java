package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructs an UnmarkCommand with the specified input.
     *
     * @param input the input string containing the task number to unmark
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     *
     * @param tasks   the task list containing the task to unmark
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws MondayException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1; // Extracts task number from input
        if (tasks.isValidTaskNumber(taskNumber)) {
            tasks.markAsNotDone(taskNumber); // Method to mark the task as not done
            ui.showTaskUnmarked(tasks.get(taskNumber)); // Show unmarked task message
            storage.save(tasks.getTasks()); // Save the updated tasks
        } else {
            throw new MondayException("    Invalid task number.");
        }
    }
}
