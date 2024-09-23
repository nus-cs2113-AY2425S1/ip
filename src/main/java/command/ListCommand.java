package command;

import data.Storage;
import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    private static final String EMPTY_LIST_MESSAGE = "No tasks added, add more now!";
    private static final String NONEMPTY_LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * List out all task in the task list
     * @param tasks Task list that stores all the tasks
     * @param storage File to save and load the task list
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        System.out.println(NONEMPTY_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1)
                    + "."
                    + currentTask);
        }
    }
}
