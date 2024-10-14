package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD= "mark";

    /**
     * Executes the mark command based on user input.
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
        taskManager.markTask(taskIndex - 1);
        Ui.displayMarkMessage(taskManager.getTask(taskIndex - 1));
        taskManager.updateStorage();
    }

}
