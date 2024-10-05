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
        String[] parts = input.trim().split("\\s+"); // Split input by spaces
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MondayException(" Please provide a valid task number to unmark.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1].trim()) - 1; // Convert to 0-based index
            if (tasks.isValidTaskNumber(taskNumber)) {
                tasks.markAsNotDone(taskNumber);
                ui.showTaskUnmarked(tasks.get(taskNumber));
                storage.save(tasks.getTasks());
            } else {
                throw new MondayException(" Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new MondayException(" Please enter a valid task number.");
        }
    }
}
