package CassHelpers.commands;

import CassHelpers.exceptions.TaskAlreadyMarkedException;
import CassHelpers.exceptions.TaskNotFoundException;
import CassHelpers.types.Task;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * The MarkCommand class represents a command to mark a task as completed in the task list.
 * It updates the task as completed, saves the changes, and handles related exceptions.
 */
public class MarkCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final int taskIndex;

    /**
     * Constructor for MarkCommand.
     *
     * @param tasks     The task list containing tasks.
     * @param taskIndex The index of the task to be marked as completed.
     */
    public MarkCommand(TaskList tasks, int taskIndex) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @throws TaskNotFoundException       If the specified task index does not exist in the list.
     * @throws TaskAlreadyMarkedException  If the task is already marked as completed.
     */
    @Override
    public void execute() throws TaskNotFoundException, TaskAlreadyMarkedException {
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (taskList.get(taskIndex - 1).getIsCompleted()) {
            throw new TaskAlreadyMarkedException("Task has already been marked complete");
        } else {
            taskList.get(taskIndex - 1).setCompleted(true);
            this.storage.writeTasksToFile(this.taskList);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(taskIndex - 1).toString());
        }
    }
}
