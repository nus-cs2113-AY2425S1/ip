package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;
import atom.exception.EmptyTaskIdException;
import atom.exception.TaskIdOutOfBoundsException;

/**
 * Represents a command that marks a task as done or undone.
 */
public class MarkCommand extends Command{

    protected String[] userInputSplit;
    protected String command;

    public MarkCommand(String[] words, String command) {
        userInputSplit = words;
        this.command = command;
    }

    /**
     * Marks a specified task as done or undone using the task id.
     * <p>
     * If the user command only contains the <code>mark</code> or <code>unmark</code> keyword,
     * an <code>EmptyTaskIdException</code> is thrown and caught in the method.
     * <p>
     * If the task id is not an <code>int</code> value, the <code>NumberFormatException</code>
     * will be caught in the method.
     * <p>
     * If the task id is an <code>int</code> value but is invalid, a
     * <code>TaskIdOutOfBoundsException</code> will be thrown and caught in the method.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userInputSplit.length == 1) {
                throw new EmptyTaskIdException();
            }

            //converting task id to a zero-based index
            int taskId = Integer.parseInt(userInputSplit[1]) - 1;

            if (taskId >= tasks.getTasksListSize() || taskId < 0) {
                throw new TaskIdOutOfBoundsException(tasks.getTasksListSize());
            }

            if (command.equals("mark")) {
                tasks.markAsDone(taskId);
                ui.showTaskStatusMessage(tasks, taskId, command);
            } else {
                tasks.markAsUndone(taskId);
                ui.showTaskStatusMessage(tasks, taskId, command);
            }

        } catch (EmptyTaskIdException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showNumberFormatExceptionMessage();
        } catch (TaskIdOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
