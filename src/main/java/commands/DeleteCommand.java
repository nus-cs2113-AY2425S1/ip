package commands;

import exception.InvalidIndexException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.DELETE_COMMAND;

/**
 * Represents a command that deletes a specified task from Bento.
 * This class extends {@link Command} and handles the execution of the delete command,
 * which removes a task from the task list based on user input.
 */
public class DeleteCommand extends Command {
    private String userInput;

    /**
     * Constructs a DeleteCommand with the user's input for the task to delete.
     *
     * @param userInput The input string indicating which task to delete.
     */
    public DeleteCommand(String userInput) {
        super(DELETE_COMMAND);
        this.userInput = userInput;
    }

    /**
     * Executes the delete command, removing the specified task from the task list.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws InvalidIndexException If the task index is invalid.
     * @throws MissingTaskException If the specified task does not exist.
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException,
            MissingTaskException, SaveFileErrorException {
        try {
            userInput = parser.removeDeletePrefix(userInput);
            int index = Integer.parseInt(userInput) - 1;
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            saveTask(storage, tasks, ui);
            ui.printDeleteTaskSuccessMessage(task, tasks);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }
}
