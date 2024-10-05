package command;

import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
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
     * @throws MondayException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
        if (tasks.isValidTaskNumber(taskNumber)) {
            ui.showTaskRemoved(tasks.remove(taskNumber), tasks.size());
            storage.save(tasks.getTasks());
        } else {
            throw new MondayException(" Invalid task number.");
        }
    }
}
