package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends AddCommand {

    public AddEventCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidCommandException("Missing start or end date and time");
        }

        String[] parts = userInput.split(" /from ");
        String preCleanedTaskContent = parts[0].trim();

        if (preCleanedTaskContent.length() <= 5) {
            throw new InvalidCommandException("The description of the event task is missing");
        }

        String taskContent = preCleanedTaskContent.substring(5).trim();
        String[] dateTime = parts[1].split(" /to ");

        if (dateTime.length < 2) {
            throw new InvalidCommandException("The start or end date and time are missing");
        }

        String startString = dateTime[0].trim();
        String endString = dateTime[1].trim();
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime start = LocalDateTime.parse(startString, formatter);
            LocalDateTime end = LocalDateTime.parse(endString, formatter);
            Task task = new Event(taskContent, start, end);
            storage.storageInsert(task);
        }catch (DateTimeParseException e){
            throw new InvalidCommandException("Invalid date format! Please use dd/MM/yyyy HH:mm.");
        }
        storage.storageList();
    }
}
