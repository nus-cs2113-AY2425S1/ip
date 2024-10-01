package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;

import niwa.messages.NiwaExceptionMessages;
import niwa.messages.NiwaMesssages;

import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code UnmarkCommand} class handles unmarking a task as not done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_GUIDE = "unmark [task index]: Unmark the task at the given index.";
    public static final String[] COMMAND_KEYWORDS = {""};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length &&
                arguments.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Marks a task as undone.
     *
     * @return A {@code CommandResult} containing the result of the unmarking.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String indexString = arguments.get(COMMAND_KEYWORDS[0]); // Get the task index

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            // Parse the index from the arguments array (convert to zero-based index).
            int index = Integer.parseInt(indexString) - 1;

            Task temp = TaskList.getInstance().findTask(index); // Find the task by index
            temp.markAsUndone(); // Unmark the task

            // Add success messages
            messages.add(String.format(NiwaMesssages.MESSAGE_UNMARK_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());

            messages.add(autoSaveTasks()); // Auto-save tasks

        } catch (NiwaTaskIndexOutOfBoundException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_UNMARK_FAILED, e.getMessage()));
        } catch (NumberFormatException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_UNMARK_FAILED,
                    NiwaExceptionMessages.MESSAGE_INDEX_NUMBER_FORMAT));
        }

        return new CommandResult(messages);
    }
}
