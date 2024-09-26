package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.TaskList;
import quinn.ui.Ui;

public class ListCommand implements Command {
    public ListCommand() {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException {
        if (taskList.getNumOfTasks() != 0) {
            String response = ui.tasksInListMessage(taskList);
            ui.displayResponse(response);
        } else {
            throw new QuinnException("There are no tasks in your list!");
        }
    }
}
