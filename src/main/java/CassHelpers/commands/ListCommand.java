package CassHelpers.commands;

import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

/**
 * Command class responsible for listing all the tasks in the task list.
 * It prints out all the tasks currently in the task list.
 */
public class ListCommand implements Command {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new ListCommand.
     *
     * @param tasks The TaskList object containing tasks to be listed.
     */
    public ListCommand(TaskList tasks) {
        this.taskList = tasks.getTaskList();
    }

    /**
     * Executes the command by listing all tasks in the task list.
     */
    @Override
    public void execute() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }
}
