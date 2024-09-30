package CassHelpers.commands;

import CassHelpers.exceptions.TaskAlreadyUnmarkedException;
import CassHelpers.exceptions.TaskNotFoundException;
import CassHelpers.types.Task;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * The UnmarkCommand class represents a command to mark a task as not completed in the task list.
 * It updates the task as incomplete, saves the changes, and handles related exceptions.
 */
public class UnmarkCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final int taskIndex;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param tasks     The task list containing tasks.
     * @param taskIndex The index of the task to be marked as incomplete.
     */
    public UnmarkCommand(TaskList tasks, int taskIndex) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as incomplete.
     *
     * @throws TaskNotFoundException        If the specified task index does not exist in the list.
     * @throws TaskAlreadyUnmarkedException If the task is already marked as incomplete.
     */
    @Override
    public void execute() throws TaskNotFoundException, TaskAlreadyUnmarkedException {
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (!taskList.get(taskIndex - 1).getIsCompleted()) {
            throw new TaskAlreadyUnmarkedException("Task has already been marked incomplete");
        } else {
            taskList.get(taskIndex - 1).setCompleted(false);
            this.storage.writeTasksToFile(this.taskList);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + taskList.get(taskIndex - 1).toString());
        }
    }
}
