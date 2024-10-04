package niwa.command;

import niwa.Niwa;
import niwa.data.storage.Storage;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.messages.NiwaMesssages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Command} class is an abstract base class for all commands in the Niwa chatbot.
 * It defines the structure for executing commands and provides common functionality such as
 * task auto-saving.
 */
public abstract class Command {
    public static final String COMMAND_WORD = ""; // The word associated with the command
    public static final String COMMAND_GUIDE = ""; // A guide or description of the command
    public static final String[] COMMAND_KEYWORDS = {}; // Keywords for arguments

    protected Map<String, String> arguments = new HashMap<>(); // A map to hold command arguments

    /**
     * Executes the command and returns the result.
     *
     * @return A {@code CommandResult} with execution messages.
     * @throws NiwaInvalidArgumentException If the provided arguments are invalid for the command.
     */
    public abstract CommandResult execute() throws NiwaInvalidArgumentException;

    /**
     * Sets the arguments for the command.
     *
     * @param arguments A map of arguments where the key is the argument name and the value is the argument value.
     */
    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Automatically saves the current task list to the storage file.
     *
     * @return A message indicating the success or failure of the save operation.
     */
    public static String autoSaveTasks() {
        Storage storage = new Storage(Niwa.getOutputFilePath()); // Create a storage instance for the output file

        String message;
        try {
            storage.writeTaskList(TaskList.getInstance().getTaskList()); // Write the current task list to the file
            message = NiwaMesssages.MESSAGE_SAVE_COMPLETE_DEFAULT; // Success message
        } catch (IOException e) {
            message = String.format(NiwaMesssages.MESSAGE_SAVE_FAILED, e.getMessage()); // Error message if save fails
        }
        return message; // Return the status message
    }
}
