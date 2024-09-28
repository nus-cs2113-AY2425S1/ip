package CassHelpers.commands;

import CassHelpers.exceptions.TaskAlreadyUnmarkedException;
import CassHelpers.exceptions.TaskNotFoundException;
import CassHelpers.types.Task;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

public class UnmarkCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final int taskIndex;

    public UnmarkCommand(TaskList tasks, int taskIndex) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.taskIndex = taskIndex;
    }

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
