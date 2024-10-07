package command;

import exception.UserInputException;
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
     * @throws UserInputException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UserInputException {
        String[] parts = input.trim().split("\\s+"); // Split input by spaces
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new UserInputException(" Please provide a valid task number to mark.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1].trim()) - 1; // Convert to 0-based index
            if (tasks.isValidTaskNumber(taskNumber)) {
                tasks.markAsDone(taskNumber);
                ui.showTaskMarkedDone(tasks.get(taskNumber));
                storage.save(tasks.getTasks());
            } else {
                throw new UserInputException(" Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new UserInputException(" Please enter a numerical task number.");
        }
    }
}
