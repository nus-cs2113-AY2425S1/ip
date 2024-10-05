package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructs a MarkCommand with the specified input.
     *
     * @param input the input string containing the task number to mark
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks   the task list containing the task to mark
     * @param ui      the UI for user interaction
     * @param storage the storage for saving tasks
     * @throws MondayException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
        if (tasks.isValidTaskNumber(taskNumber)) {
            tasks.markAsDone(taskNumber);
            ui.showTaskMarkedDone(tasks.get(taskNumber));
            storage.save(tasks.getTasks());
        } else {
            throw new MondayException("Invalid task number.");
        }
    }
}
