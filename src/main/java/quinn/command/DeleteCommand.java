package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

public class DeleteCommand implements Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            Task taskDeleted = taskList.deleteTask(taskNum);

            String response = ui.taskDeletedMessage(taskDeleted)
                    + System.lineSeparator()
                    + ui.numOfTasksInListMessage(taskList);
            ui.displayResponse(response);

            storage.saveTasksToFile(taskList);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
