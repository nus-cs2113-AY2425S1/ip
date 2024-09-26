package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task (mark it as not done).
 */
public class UnmarkCommand implements Command {
    private final int taskNum;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNum the number of the task to be unmarked
     */
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

    /**
     * Marks a task as not done based on all tasks in the list.
     *
     * @param taskList the list of all tasks
     * @return the task marked as not done
     * @throws QuinnException if the task number is invalid
     */
    public Task markTaskNotDoneBasedOnAllTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    /**
     * Marks a task as not done based on filtered tasks in the list.
     *
     * @param taskList the list of filtered tasks
     * @return the task marked as not done
     * @throws QuinnException if the task number is invalid
     */
    public Task markTaskNotDoneBasedOnFilteredTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.markNotDone(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
