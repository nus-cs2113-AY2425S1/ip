package taskpackage; // Package for Task-related classes

import customexceptions.EventConstructorException; // Import custom exception for Event tasks

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific start and end time, extending the Task class.
 */
public class Event extends Task {

    private final LocalDateTime fromDateTime; // Stores the event start time
    private final LocalDateTime toDateTime;   // Stores the event end time
    private final static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm"; // Format for date and time
    private static final String EVENT_COMMAND = "event ";
    
    /**
     * Constructor for creating an Event task.
     *
     * @param inputString        The input string containing the task description, start time, and end time.
     * @param tasks              The task list to which the task is added.
     * @param constructorMessage A flag to indicate whether to display a constructor message.
     * @throws EventConstructorException if the task description, start time, or end time is invalid.
     */
    public Event(String inputString, TaskList tasks, boolean constructorMessage) throws EventConstructorException {
        super(inputString.split(" /from ")[0], tasks);
        this.inputString = inputString;
        this.storageString = EVENT_COMMAND + inputString;

        // Check if both "/from" and "/to" keywords are present and the task description is valid
        if (!(inputString.contains(" /from ") & inputString.contains(" /to ")) || this.taskString.isEmpty()) {
            throw new EventConstructorException(inputString);
        }

        // Parse the start and end times
        String[] fromToStrings = inputString.replace("event ", "").split(" /from ")[1].split(" /to ");
        if (fromToStrings.length != 2) {
            throw new EventConstructorException(inputString);
        }

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            this.fromDateTime = LocalDateTime.parse(fromToStrings[0], dateTimeFormatter);
            this.toDateTime = LocalDateTime.parse(fromToStrings[1], dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new EventConstructorException(inputString);
        }

        // Display a confirmation message if the constructorMessage flag is true
        if (constructorMessage) {
            constructorMessage();
        }
    }

    /**
     * Returns the task's string with a checkbox, start time, and end time.
     *
     * @return The formatted task string with event details.
     */
    @Override
    public String checkboxString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return "[E]" + super.checkboxString() + " (from: " + fromDateTime.format(dateTimeFormatter) + " to: " + toDateTime.format(dateTimeFormatter) + ")";
    }
}
