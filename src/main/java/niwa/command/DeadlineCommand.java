package niwa.command;

import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.data.task.Deadline;
import niwa.data.task.Task;
import niwa.data.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code DeadlineCommand} class handles the addition of deadline tasks.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_GUIDE = "deadline [task description] /by [yyyy-mm-dd] (optional)[HHmm]: "
            + "Add a new deadline to our list.";
    public static final String[] COMMAND_KEYWORDS = {"", "/by"};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length && arguments.containsKey(COMMAND_KEYWORDS[0]) &&
                arguments.containsKey(COMMAND_KEYWORDS[1]);
    }

    /**
     * Adds a new deadline task to the task list.
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
        String byDay = arguments.get(COMMAND_KEYWORDS[1]); // Deadline date

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            Task temp = new Deadline(description, byDay); // Create new deadline task
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
