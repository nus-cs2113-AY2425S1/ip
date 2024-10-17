package niwa.command;

import niwa.Niwa;
import niwa.data.storage.Storage;
import niwa.data.task.Task;
import niwa.data.task.TaskList;
import niwa.exception.NiwaDuplicateTaskException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;
import niwa.utils.NiwaUtils;

import java.util.ArrayList;
import java.io.IOException;

/**
 * The {@code ReadCommand} class handles reading tasks from a specified text file
 * and adding them to the task list.
 */
public class ReadCommand extends Command {
    public static final String COMMAND_WORD = "read";
    public static final String COMMAND_GUIDE = "read [.txt file path]: Read the tasks in the file and add to the list.";
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
     * Executes the read command to load tasks from a file.
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
        ArrayList<Task> tasks; // Task list to hold loaded tasks

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution

        try {
            // Print the file path when read from external file
            if (!dataPath.equals(Niwa.getOutputFilePath())) {
                messages.add(String.format(NiwaMesssages.MESSAGE_READ_INFORM, dataPath)); // Inform reading
            }
            tasks = storage.loadTaskList(); // Load tasks
        } catch (IOException e) {
            messages.add(String.format(NiwaMesssages.MESSAGE_READ_FAILED, e.getMessage()));
            return new CommandResult(messages);
        }

        // Add tasks to the task list
        for (Task task : tasks) {
            try {
                messages.add("Adding..." + task.getFileOutput());
                TaskList.getInstance().addTask(task);
            } catch (NiwaDuplicateTaskException e) {
                messages.add(String.format(NiwaMesssages.MESSAGE_ADD_FAILED, e.getMessage()));
            }
        }

        // Auto-save to default file if read from external file
        if (!dataPath.equals(Niwa.getOutputFilePath())) {
            messages.add(autoSaveTasks());
        }

        return new CommandResult(messages);
    }
}
