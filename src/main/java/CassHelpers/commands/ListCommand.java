package CassHelpers.commands;

import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

public class ListCommand implements Command {
    private final ArrayList<Task> taskList;

    public ListCommand(TaskList tasks) {
        this.taskList = tasks.getTaskList();
    }

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
