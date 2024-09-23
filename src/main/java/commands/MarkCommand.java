package commands;

import exception.InvalidIndexException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.MARK_COMMAND;

/**
 * Represents a command that marks a task as completed or uncompleted in Bento.
 * This class extends {@link Command} and handles the execution of the mark command,
 * including updating the task status based on the user's input.
 */
public class MarkCommand extends Command {
    private final boolean toMark;
    private String taskIndex;

    /**
     * Constructs a MarkCommand with the specified status and task index.
     *
     * @param toMark Indicates whether to mark the task as completed (true) or uncompleted (false).
     * @param taskIndex The index of the task to mark.
     */
    public MarkCommand(boolean toMark, String taskIndex) {
        super(MARK_COMMAND);
        this.toMark = toMark;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, updating the task status and saving the task list.
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
            taskIndex = parser.removeMarkPrefix(taskIndex);
            int index = Integer.parseInt(taskIndex) - 1;
            tasks.updateTask(toMark, index);
            ui.printMarkUpdate(tasks, toMark, index);
            saveTask(storage, tasks, ui);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }
}
