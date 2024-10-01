package niwa.command;

import niwa.data.storage.Storage;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.utils.NiwaUtils;

import java.util.ArrayList;
import java.io.IOException;

/**
 * The {@code SaveCommand} class handles saving the current task list to a specified text file.
 */
public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    public static final String COMMAND_GUIDE = "save [.txt file path]: Save the task list to the given path.";
    public static final String[] COMMAND_KEYWORDS = {""};
    public static final String PATH_FORMAT = "^(?:[a-zA-Z]:[\\\\/]|[\\\\/]|\\.\\/)?([\\w.-]+[\\\\/])*[\\w.-]+\\.txt$";

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        if (arguments.size() != COMMAND_KEYWORDS.length) {
            return false; // Validate argument count
        }
        for (String keyword : COMMAND_KEYWORDS) {
            if (!arguments.containsKey(keyword)) {
                return false; // Validate presence of keywords
            }
        }
        return true; // Arguments are valid
    }

    /**
     * Executes the save command to write the current task list to a file.
     *
     * @return A {@code CommandResult} with execution messages.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String dataPath = arguments.get(COMMAND_KEYWORDS[0]); // Get file path
        if (!NiwaUtils.isMatch(dataPath, PATH_FORMAT)) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid path format
        }

        Storage storage = new Storage(dataPath); // Create storage instance
        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        // Write tasks to file
        try {
            storage.writeTaskList(TaskList.getInstance().getTaskList());
            messages.add(String.format(NiwaMesssages.MESSAGE_SAVE_COMPLETE, dataPath)); // Success message
        } catch (IOException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_SAVE_FAILED, e.getMessage()));
        }

        return new CommandResult(messages);
    }
}
