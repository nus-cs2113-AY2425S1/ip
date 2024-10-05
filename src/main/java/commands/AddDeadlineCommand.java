package commands;

import exceptions.InvalidCommandException;
import taskmanager.taskManager;
import tasks.Task;
import tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The AddDeadlineCommand class handles the creation of a deadline task based on the user's input.
 * It parses the task description and deadline date from the input, validates them, and adds the task
 * to storage if valid.
 */

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the add deadline command by parsing the user input to create a new Deadline task.
     * The task description and deadline date are extracted from the input and validated.
     * If valid, the task is inserted into storage. If invalid, an exception is thrown.
     *
     * @param storage The storage object that manages the task list.
     * @throws InvalidCommandException If the task description or deadline is invalid or in the wrong format.
     */

    @Override
    public void execute(taskManager storage) throws InvalidCommandException {
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
        System.out.println("____________________________________________________________");
        storage.storageList();
    }
}

