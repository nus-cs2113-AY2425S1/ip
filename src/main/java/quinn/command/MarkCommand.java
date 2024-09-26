package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int taskNum;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNum the number of the task to be marked as done
     */
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

    /**
     * Marks a task as done based on all tasks in the list.
     *
     * @param taskList the list of all tasks
     * @return the task marked as done
     * @throws QuinnException if the task number is invalid
     */
    public Task markTaskDoneBasedOnAllTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.markDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    /**
     * Marks a task as done based on filtered tasks in the list.
     *
     * @param taskList the list of filtered tasks
     * @return the task marked as done
     * @throws QuinnException if the task number is invalid
     */
    public Task markTaskDoneBasedOnFilteredTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.markDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
