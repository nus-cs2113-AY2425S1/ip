package taskpackage; // Package for Task-related classes

import customexceptions.DeadlineConstructorException;
import customexceptions.EventConstructorException; // Import custom exception for Event tasks

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Event class, a child class of Task, represents a task with a specific start and end time
public class Event extends Task {

    private final LocalDateTime fromDateTime; // Stores the event start time
    private final LocalDateTime toDateTime;   // Stores the event end time
    private final static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm"; // Format for date and time
    private static final String EVENT_COMMAND = "event ";


    // Constructor for creating an Event task
    public Event(String inputString, TaskList tasks, boolean constructorMessage) throws EventConstructorException {
        // Call the parent class (Task) constructor with the task description (before the "/from" keyword)
        super(inputString.split(" /from ")[0], tasks);

        this.inputString = inputString;
        this.storageString = EVENT_COMMAND + inputString;

        // Validate if both "/from" and "/to" keywords are present, and the task description is not empty
        if (!(inputString.contains(" /from ") & inputString.contains(" /to ")) || this.taskString.isEmpty()) {
            throw new EventConstructorException(inputString); // Throw custom exception if validation fails
        }

        // Split the input to extract the start and end times
        String[] fromToStrings = inputString.replace("event ", "").split(" /from ")[1].split(" /to ");

        // Check if both "from" and "to" times are provided
        if (fromToStrings.length != 2) {
            throw new EventConstructorException(inputString); // Throw custom exception if either is missing
        }

        // Set the from and to times for the event
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            this.fromDateTime = LocalDateTime.parse(fromToStrings[0], dateTimeFormatter);
            this.toDateTime = LocalDateTime.parse(fromToStrings[1], dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new EventConstructorException(inputString);
        }

        // Display a confirmation message when an Event task is successfully created
        if (constructorMessage) {
            constructorMessage();
        }
    }

    // Override the checkboxString method to include the "[E]" tag, start time, and end time
    @Override
    public String checkboxString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        // Call the parent method and add the "[E]" tag along with the event's start and end times
        return "[E]" + super.checkboxString() + " (from: " + fromDateTime.format(dateTimeFormatter) + " to: " + toDateTime.format(dateTimeFormatter) + ")";
    }
}
