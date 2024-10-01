package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;
import niwa.messages.NiwaShortMessages;

import niwa.data.task.TaskList;

import niwa.ui.NiwaUI;

import java.util.ArrayList;

/**
 * The {@code ClearCommand} class handles the clearing of all tasks in the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_GUIDE = "clear: Clear all tasks in the list.";
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
     * Clears all tasks in the task list.
     *
     * @return A {@code CommandResult} containing the result of the clearing operation.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        NiwaUI ui = new NiwaUI();
        ui.printMiddleMessage(NiwaMesssages.MESSAGE_CLEAR_ASK); // Ask for confirmation

        String response = ui.getUserCommand().toUpperCase().trim(); // Get user confirmation

        if (response.equals(NiwaShortMessages.YES_MESSAGE)) {
            TaskList.getInstance().clearTaskList(); // Clear the task list

            messages.add(NiwaMesssages.MESSAGE_CLEAR_SUCCESS); // Success message
            messages.add(autoSaveTasks()); // Auto-save tasks
        } else {
            messages.add(NiwaMesssages.MESSAGE_CLEAR_CANCEL); // Cancellation message
        }

        // Inform user of the current task list size
        messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                TaskList.getInstance().getTaskListSize()));

        return new CommandResult(messages);
    }
}
