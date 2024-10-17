package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code MarkCommand} class handles the marking of tasks as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_GUIDE = "mark [task index]: Mark the task at the given index as done.";
    public static final String[] COMMAND_KEYWORDS = {""};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length
                && arguments.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Marks a task as done.
     *
     * @return A {@code CommandResult} containing the result of the marking action.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]); // Get the index as a string

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            // Parse the index from the arguments (convert to zero-based index)
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().findTask(index); // Find the task
            temp.markAsDone(); // Mark the task as done

            // Add success messages
            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());

            messages.add(autoSaveTasks()); // Auto-save tasks

        } catch (NiwaTaskIndexOutOfBoundException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_FAILED, e.getMessage()));
        } catch (NumberFormatException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_MARK_FAILED,
                    NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT));
        }

        return new CommandResult(messages);
    }
}
