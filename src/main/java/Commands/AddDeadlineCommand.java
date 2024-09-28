package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;
import tasks.Task;
import tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        String[] parts = userInput.substring(8).split(" /by ");
        if (parts[0].trim().isEmpty()) {
            throw new InvalidCommandException("The description of the deadline task cannot be empty");
        }
        if (parts.length < 2) {
            throw new InvalidCommandException("The deadline of the task cannot be empty");
        }
        String taskContent = parts[0].trim();
        String deadlineString = parts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
            Task task = new Deadline(taskContent, deadline);
            storage.storageInsert(task);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format! Please use dd/MM/yyyy HH:mm.");
        }
        storage.storageList();
    }
}

