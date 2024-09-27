package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;

public class MarkCommand extends Command {

    protected String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        // Split the user input to extract the index of the task to mark
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new InvalidCommandException("Provide index of the task to mark");
        }

        try {
            int index = Integer.parseInt(parts[1]);
            System.out.println(index);
            storage.storageMark(index);      // Mark the task
            storage.storageList();           // List the updated tasks
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format");
        }
    }
}

