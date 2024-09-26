package quinn.command;

import quinn.exception.QuinnException;
import quinn.storage.Storage;
import quinn.task.Task;
import quinn.task.TaskList;
import quinn.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskNum;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNum the number of the task to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws QuinnException, IOException {
        Task taskDeleted = !taskList.hasFilter()
                ? deleteTaskBasedOnAllTasks(taskList)
                : deleteTaskBasedOnFilteredTasks(taskList);

        if (taskList.getNumOfFilteredTasks() == 0) {
            // Reset the List of filteredTasks when the List is empty
            // This will clear the List of filteredTasks
            taskList.resetFilteredTasks();
        }

        String response = ui.taskDeletedMessage(taskDeleted)
                + System.lineSeparator()
                + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }

    /**
     * Deletes a task based on all tasks in the list.
     *
     * @param taskList the list of all tasks
     * @return the deleted task
     * @throws QuinnException if the task number is invalid
     */
    public Task deleteTaskBasedOnAllTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            return taskList.deleteTask(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    /**
     * Deletes a task based on filtered tasks in the list.
     *
     * @param taskList the list of filtered tasks
     * @return the deleted task
     * @throws QuinnException if the task number is invalid
     */
    public Task deleteTaskBasedOnFilteredTasks(TaskList taskList) throws QuinnException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfFilteredTasks()) {
            return taskList.deleteTask(taskNum);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
