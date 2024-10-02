package atom;

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

            tasks.deleteTask(taskId);
            ui.showDeleteTaskMessage(tasks, taskId);

        } catch (EmptyTaskIdException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showNumberFormatExceptionError();
        } catch (TaskIdOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
