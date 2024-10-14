package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;

/**
 * Represents a command to unmark a task in the task list.
 */
public class UnmarkCommand extends Command {
    /**
     * The command word for unmarking tasks.
     */
    public static final String COMMAND_WORD= "unmark";

    /**
     * Executes the unmark command based on user input.
     *
     * @param inputs      The user input containing the command and task index.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        int taskIndex;
        try {
            taskIndex = validateIndex(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
            return;
        }
        taskManager.unmarkTask(taskIndex - 1);
        Ui.displayUnmarkMessage(taskManager.getTask(taskIndex - 1));
        taskManager.updateStorage();
    }
}
