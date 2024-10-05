package commands;

import exceptions.InvalidCommandException;
import taskmanager.taskManager;

/**
 * The DeleteCommand class handles the deletion of a task based on the user's input.
 * It parses the task index from the input, validates it, and removes the task from storage.
 */

public class DeleteCommand extends Command {

    protected final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the delete command by parsing the user input to extract the task index.
     * The method then deletes the task from storage and prints the remaining tasks.
     * If the task number is invalid or missing, an exception is thrown.
     *
     * @param storage The storage object that manages the task list.
     * @throws InvalidCommandException If the task number is invalid or missing.
     */

    @Override
    public void execute(taskManager storage) throws InvalidCommandException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new InvalidCommandException("Provide index of the task to delete");
        }

        try {
            // Parse the index and delete the task
            int index = Integer.parseInt(parts[1]);
            System.out.println("I have removed this task: ");
            storage.storagePrintTask(index);
            storage.storageDelete(index);
            System.out.println("____________________________________________________________");
            storage.storageList();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format");
        }
    }
}
