package ryan.commands;

import ryan.utility.TaskList;
import ryan.utility.Ui;

import ryan.tasks.Task;

import java.util.ArrayList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks.
     *
     * @param taskList The task list containing the tasks to display.
     * @param ui The user interface to display the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        Ui.printTasks(tasks);
    }
}
