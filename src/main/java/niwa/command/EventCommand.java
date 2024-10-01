package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;

import niwa.messages.NiwaMesssages;

import niwa.data.task.Event;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code EventCommand} class handles the addition of event tasks.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_GUIDE = "event [task description] "
            + "/from [yyyy-mm-dd] (optional)[HHmm] /to [yyyy-mm-dd] (optional)[HHmm]: "
            + "Add a new event to our list.";
    public static final String[] COMMAND_KEYWORDS = {"", "/from", "/to"};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length &&
                arguments.containsKey(COMMAND_KEYWORDS[0]) &&
                arguments.containsKey(COMMAND_KEYWORDS[1]) &&
                arguments.containsKey(COMMAND_KEYWORDS[2]);
    }

    /**
     * Adds a new event task to the task list.
     *
     * @return A {@code CommandResult} containing the result of the addition.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String description = arguments.get(COMMAND_KEYWORDS[0]); // Task description
        String fromDay = arguments.get(COMMAND_KEYWORDS[1]); // Start date
        String toDay = arguments.get(COMMAND_KEYWORDS[2]); // End date

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            Task temp = new Event(description, fromDay, toDay); // Create new event task
            TaskList.getInstance().addTask(temp); // Add task to list

            // Add success messages
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_SUCCESS, temp.getType()));
            messages.add("\t" + temp.getFullInfo());
            messages.add(String.format(NiwaMesssages.MESSAGE_LIST_SIZE_INFORM,
                    TaskList.getInstance().getTaskListSize())); // Show remaining tasks

            messages.add(autoSaveTasks()); // Auto-save tasks

        } catch (NiwaDuplicateTaskException | NiwaException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
