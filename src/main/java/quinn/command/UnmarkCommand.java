package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class UnmarkCommand implements Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            Task taskNotDone = taskList.markNotDone(taskNum);

            String response = ui.taskNotDoneMessage(taskNotDone);
            ui.displayResponse(response);

            storage.saveTasksToFile(taskList);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
