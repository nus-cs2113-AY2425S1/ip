package atom;

import atom.exception.EmptyTaskIdException;
import atom.exception.TaskIdOutOfBoundsException;

public class MarkCommand extends Command{

    protected String[] userInputSplit;
    protected String command;

    public MarkCommand(String[] words, String command) {
        userInputSplit = words;
        this.command = command;
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
