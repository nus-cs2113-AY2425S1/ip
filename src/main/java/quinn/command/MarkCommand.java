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
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            Task taskDone = taskList.markDone(taskNum);

            String response = ui.taskDoneMessage(taskDone);
            ui.displayResponse(response);

            storage.saveTasksToFile(taskList);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
