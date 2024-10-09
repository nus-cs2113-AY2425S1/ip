package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;
import atom.exception.EmptyTaskIdException;
import atom.exception.TaskIdOutOfBoundsException;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command{

    private String[] userInputSplit;

    public DeleteCommand(String[] words) {
        userInputSplit = words;
    }

    /**
     * Deletes a specified task from the task list using the task id.
     * <p>
     * If the user command only contains the <code>delete</code> keyword,
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

            ui.showDeleteTaskMessage(tasks, taskId);
            tasks.deleteTask(taskId);

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
