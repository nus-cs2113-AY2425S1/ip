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
        Task taskNotDone = !taskList.hasFilter()
                ? markTaskNotDoneBasedOnAllTasks(taskList)
                : markTaskNotDoneBasedOnFilteredTasks(taskList);

        String response = ui.taskNotDoneMessage(taskNotDone);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }

    public Task markTaskNotDoneBasedOnAllTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    public Task markTaskNotDoneBasedOnFilteredTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
