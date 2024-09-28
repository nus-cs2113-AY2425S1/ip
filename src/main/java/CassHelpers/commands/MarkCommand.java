package CassHelpers.commands;

import CassHelpers.exceptions.TaskAlreadyMarkedException;
import CassHelpers.exceptions.TaskNotFoundException;
import CassHelpers.types.Task;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

public class MarkCommand implements Command {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private final int taskIndex;

    public MarkCommand(TaskList tasks, int taskIndex) {
        this.taskList = tasks.getTaskList();
        this.storage = tasks.getStorage();
        this.taskIndex = taskIndex;
    }

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
