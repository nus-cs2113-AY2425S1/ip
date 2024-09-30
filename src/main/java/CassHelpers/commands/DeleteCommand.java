package CassHelpers.commands;

import CassHelpers.exceptions.TaskNotFoundException;
import CassHelpers.types.Task;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * Command class responsible for deleting a task from the task list.
 * It removes the task at the specified index from the task list and updates the storage.
 */
public class DeleteCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final int taskIndex;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param tasks The TaskList object from which the task will be deleted.
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(TaskList tasks, int taskIndex) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list.
     *
     * @throws TaskNotFoundException If the task index is invalid or out of bounds.
     */
    @Override
    public void execute() throws TaskNotFoundException {
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else {
            String removedTaskDescription = taskList.get(taskIndex - 1).toString();
            taskList.remove(taskIndex - 1);
            storage.writeTasksToFile(this.taskList);
            System.out.println("Noted, I've removed this task:");
            System.out.println(" " + removedTaskDescription);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
}
