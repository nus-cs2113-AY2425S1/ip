package niwa.command;

import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.util.ArrayList;

/**
 * The {@code ListCommand} class handles listing all current tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_GUIDE = "list: List all current tasks.";
    public static final String[] COMMAND_KEYWORDS = {};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length; // Validate argument count
    }

    /**
     * Executes the list command to retrieve and display current tasks.
     *
     * @return A {@code CommandResult} containing the task list and messages.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution
        ArrayList<Task> returnedTasks = TaskList.getInstance().getTaskList(); // Get tasks

        if (returnedTasks.isEmpty()) {
            messages.add(NiwaMesssages.MESSAGE_LIST_EMPTY); // No tasks available
        } else {
            messages.add(NiwaMesssages.MESSAGE_LIST_SUCCESS); // Tasks retrieved successfully
        }

        return new CommandResult(messages, returnedTasks);
    }
}
