package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InvalidInputException;
import nova.task.Task;

/**
 * Represents a command to mark or unmark tasks in the task manager.
 * This command allows users to update the completion status of tasks.
 */
public class MarkAndUnmarkCommand extends Command {

    /**
     * The command word for marking tasks.
     */
    public static final String COMMAND_WORD1 = "mark";

    /**
     * The command word for unmarking tasks.
     */
    public static final String COMMAND_WORD2 = "unmark";

    /**
     * Executes the mark or unmark command based on user input.
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

        if (inputs[0].equals(COMMAND_WORD1)) {
            taskManager.markTask(taskIndex - 1);
            Ui.displayMarkMessage(taskManager.getTask(taskIndex - 1));
        } else {
            taskManager.unmarkTask(taskIndex - 1);
            Ui.displayUnmarkMessage(taskManager.getTask(taskIndex - 1));
        }
        taskManager.updateStorage();
    }

    /**
     * Validates the task index provided by the user.
     *
     * @param inputs The user input containing the command and task index.
     * @return The validated task index.
     * @throws InvalidInputException If the index is invalid.
     */
    private static int validateIndex(String[] inputs) throws InvalidInputException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid index. Please provide a valid task number after the command.");
        }

        if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) {
            throw new InvalidInputException("Invalid task index: " + taskIndex + ". " +
                    "Please provide a number between 1 and " + Task.getNumberOfTasks() + ".");
        }

        return taskIndex;
    }
}
