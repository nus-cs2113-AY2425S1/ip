package Task;

import AlyBot.AlyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task, which is a task that happens within a specific start and end time.
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String formattedStartDateTime;
    protected String formattedEndDateTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, String startTime, String endTime) throws AlyException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
            formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new AlyException("Invalid Date lah bro, can use your brain anot?!");
        }
    }

    /**
     * Returns the formatted start date and time of the event.
     *
     * @return A string representing the formatted start date and time.
     */
    public String getFormattedStartDateTime() {
        return formattedStartDateTime;
    }

    /**
     * Returns the formatted end date and time of the event.
     *
     * @return A string representing the formatted end date and time.
     */
    public String getFormattedEndDateTime() {
        return formattedEndDateTime;
    }

    /**
     * Converts the Event task details to a specific format for saving to a file.
     *
     * @param taskDescription The description of the task.
     * @param status The status of the task (done or not done).
     * @return The formatted string for file storage.
     */
    @Override
    public String toFile(String taskDescription, char status) {
        int fromIndex = taskDescription.indexOf("(from: ");
        int toIndex = taskDescription.indexOf("to: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6,fromIndex-1) + "|"
                + taskDescription.substring(fromIndex+7,toIndex) + "|"
                + taskDescription.substring(toIndex+4,taskDescription.length()-1);
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation of the task, including the start and end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedStartDateTime + " to: " + formattedEndDateTime + ")";
    }
}