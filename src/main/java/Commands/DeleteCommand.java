package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

public class DeleteCommand extends Command {

    protected String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        // Split the user input to extract the index of the task to delete
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new InvalidCommandException("Provide index of the task to delete");
        }

        try {
            // Parse the index and delete the task
            int index = Integer.parseInt(parts[1]);
            System.out.println("I have removed this task: ");
            storage.storagePrintTask(index);  // Print the task before removing it
            storage.storageDelete(index);     // Delete the task
            storage.storageList();            // List the remaining tasks
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format");
        }
    }
}
