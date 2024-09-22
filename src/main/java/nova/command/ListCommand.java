package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.task.Task;

/**
 * Represents a command to list all tasks in the task manager.
 * This command retrieves and displays the current tasks for the user.
 */
public class ListCommand extends Command {

    /**
     * The command word for the List command.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the List command by displaying all tasks managed by the task manager.
     *
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(TaskList taskManager) {
        Ui.displaySeparator();
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("     " + (i + 1) + "." + taskManager.getTaskInfo(i));
        }
        Ui.displaySeparator();
    }
}
