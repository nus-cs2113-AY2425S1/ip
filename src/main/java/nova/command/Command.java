package nova.command;

import nova.exception.InvalidInputException;
import nova.task.Task;

/**
 * Represents a base class for all commands in the application.
 * This class can be extended to implement specific command functionality.
 */
public abstract class Command {
    /**
     * Validates the task index provided by the user.
     *
     * @param inputs The user input containing the command and task index.
     * @return The validated task index.
     * @throws InvalidInputException If the index is invalid.
     */
    static int validateIndex(String[] inputs) throws InvalidInputException {
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
