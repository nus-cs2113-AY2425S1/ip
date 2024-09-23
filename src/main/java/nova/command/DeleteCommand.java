package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;
import nova.task.Task;

/**
 * Represents a command to delete a task from the task list.
 * This command allows the user to specify the index of the task to be deleted.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static int taskIndex;

    /**
     * Executes the Delete command by validating the input index,
     * displaying a delete confirmation message, and removing the task from the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            validateIndex(inputs);
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
            return;
        }

        Ui.displayDeleteMessage(taskManager.getTask(taskIndex - 1));
        taskManager.removeTask(taskIndex - 1);
        taskManager.updateStorage();
    }

    /**
     * Validates the input index for the task to be deleted.
     * Ensures that the index is a valid number and within the bounds of the current task list.
     * Update the taskIndex variable
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the index is invalid or out of bounds.
     */
    private static void validateIndex(String[] inputs) throws InvalidInputException {
        try {
            taskIndex = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid index. Please provide a valid task number after the command.");
        }

        if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) {
            throw new InvalidInputException("Invalid task index: " + taskIndex + ". " +
                    "Please provide a number between 1 and " + Task.getNumberOfTasks() + ".");
        }
    }
}
