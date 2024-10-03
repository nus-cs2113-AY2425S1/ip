package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

/**
 * The UnmarkCommand class handles marking a task as not completed based on the user's input.
 * It parses the task index from the input, validates it, and unmarks the task in storage.
 */

public class UnmarkCommand extends Command {

    protected String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the unmark command by parsing the user input to extract the task index.
     * The method then unmarks the task in storage and prints the updated list of tasks.
     * If the task number is invalid or missing, an exception is thrown.
     *
     * @param storage The storage object that manages the task list.
     * @throws InvalidCommandException If the task number is invalid or missing.
     */
    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        // Split the user input to extract the index of the task to unmark
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new InvalidCommandException("Provide index of the task to unmark");
        }

        try {
            // Parse the index and unmark the task
            int index = Integer.parseInt(parts[1]);
            storage.storageUnmark(index);   // Unmark the task
            storage.storageList();          // List the updated tasks
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format");
        }
    }
}
