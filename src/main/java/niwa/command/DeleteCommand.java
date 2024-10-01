package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code DeleteCommand} class handles the deletion of tasks by index.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_GUIDE = "delete [task index]: Delete the task at the given index.";
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
     * Executes the deletion of a task based on the provided index.
     *
     * @return A {@code CommandResult} containing the result of the deletion.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]);
        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().deleteTask(index); // Delete task

            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_SUCCESS, temp.getType())); // Success message
            messages.add("\t" + temp.getFullInfo()); // Show task details
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize())); // Show remaining tasks

            messages.add(autoSaveTasks()); // Auto-save tasks

        } catch (NiwaTaskIndexOutOfBoundException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_FAILED, e.getMessage()));
        } catch (NumberFormatException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_DELETE_FAILED,
                    NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT));
        }

        return new CommandResult(messages);
    }
}
