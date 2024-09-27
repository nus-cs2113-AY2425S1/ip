package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

public class UnmarkCommand extends Command {

    protected String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

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
