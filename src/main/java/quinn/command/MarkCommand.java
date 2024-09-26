package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class MarkCommand implements Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException {
        Task taskDone = !taskList.hasFilter()
                ? markTaskDoneBasedOnAllTasks(taskList)
                : markTaskDoneBasedOnFilteredTasks(taskList);

        String response = ui.taskDoneMessage(taskDone);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }

    public Task markTaskDoneBasedOnAllTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.markDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    public Task markTaskDoneBasedOnFilteredTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.markDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
