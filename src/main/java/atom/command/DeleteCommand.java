package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;
import atom.exception.EmptyTaskIdException;
import atom.exception.TaskIdOutOfBoundsException;

public class DeleteCommand extends Command{

    protected String[] userInputSplit;

    public DeleteCommand(String[] words) {
        userInputSplit = words;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userInputSplit.length == 1) {
                throw new EmptyTaskIdException();
            }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
