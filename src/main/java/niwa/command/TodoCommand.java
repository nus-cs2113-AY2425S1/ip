package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.data.task.ToDo;

import java.util.ArrayList;

/**
 * The {@code TodoCommand} class handles the addition of to-do tasks.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_GUIDE = "todo [task description]: Add a new to-do task to our list.";
    public static final String[] COMMAND_KEYWORDS = {""};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length; // Validate argument count
    }

    /**
     * Adds a new to-do task to the task list.
     *
     * @return A {@code CommandResult} containing the result of the addition.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]);
        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            Task temp = new ToDo(description); // Create new task
            TaskList.getInstance().addTask(temp); // Add task to list

            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_SUCCESS, temp.getType())); // Success message
            messages.add("\t" + temp.getFullInfo()); // Show task details
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize())); // Show remaining tasks

            messages.add(autoSaveTasks()); // Auto-save tasks

        } catch (NiwaDuplicateTaskException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
