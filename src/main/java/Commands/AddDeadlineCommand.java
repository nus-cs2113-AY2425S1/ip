package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;
import tasks.Task;
import tasks.Deadline;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        String[] parts = userInput.split(" /by ");
        if (parts[0].trim().isEmpty()) {
            throw new InvalidCommandException("The description of the deadline task cannot be empty");
        }
        if (parts.length < 2) {
            throw new InvalidCommandException("The deadline of the task cannot be empty");
        }
        String taskContent = parts[0].trim();
        String deadline = parts[1].trim();
        Task task = new Deadline(taskContent, deadline);
        storage.storageInsert(task);
        storage.storageList();
    }
}

