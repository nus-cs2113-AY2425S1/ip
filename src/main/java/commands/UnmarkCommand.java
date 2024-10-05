package commands;

import exceptions.InvalidCommandException;
import taskmanager.taskManager;

/**
 * The UnmarkCommand class handles marking a task as not completed based on the user's input.
 * It parses the task index from the input, validates it, and unmarks the task in storage.
 */

public class UnmarkCommand extends Command {

    protected final String userInput;

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
    public void execute(taskManager storage) throws InvalidCommandException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new InvalidCommandException("Provide index of the task to unmark");
        }

        try {
            int index = Integer.parseInt(parts[1]);
            storage.storageUnmark(index);
            System.out.println("____________________________________________________________");
            storage.storageList();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format");
        }
    }
}
