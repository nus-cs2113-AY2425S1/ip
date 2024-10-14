package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;

/**
 * Represents a command to delete a task from the task list.
 * This command allows the user to specify the index of the task to be deleted.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static int taskIndex;

    /**
     * Executes the Delete command by validating the input index and removing the task from the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            taskIndex = validateIndex(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
            return;
        }

        Ui.displayDeleteMessage(taskManager.getTask(taskIndex - 1));
        taskManager.removeTask(taskIndex - 1);
        taskManager.updateStorage();
    }
}
