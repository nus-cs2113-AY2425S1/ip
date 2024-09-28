package Task;

import AlyBot.AlyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task, which is a task with a specified due date or time.
 */
public class Deadline extends Task {

    protected LocalDateTime dueDateTime;
    protected String formattedDueDateTime;

    /**
     * Constructs a Deadline task with the specified description and due time.
     *
     * @param description The description of the task.
     * @param dueTime The time or date the task is due.
     */
    public Deadline(String description, String dueTime) throws AlyException {
        super(description);
        try {
            this.dueDateTime = LocalDateTime.parse(dueTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            formattedDueDateTime = this.dueDateTime.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new AlyException("Invalid Date lah bro, can use your brain anot?!");
        }
    }

    public String getFormattedDueDateTime() {
        return formattedDueDateTime;
    }

    /**
     * Converts the Deadline task details to a specific format for saving to a file.
     *
     * @param taskDescription The description of the task.
     * @param status The status of the task (done or not done).
     * @return The formatted string for file storage.
     */
    @Override
    public String toFile(String taskDescription, char status) {
        int byIndex = taskDescription.indexOf("(by: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6, byIndex-1) + "|" + taskDescription.substring(byIndex+5,taskDescription.length()-1);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the task, including the due time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDueDateTime + ")";
    }
}