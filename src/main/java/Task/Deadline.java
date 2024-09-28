package Task;

import AlyBot.AlyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    protected LocalDateTime dueDateTime;
    protected String formattedDueDateTime;

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

    @Override
    public String toFile(String taskDescription, char status) {
        int byIndex = taskDescription.indexOf("(by: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6, byIndex-1) + "|" + taskDescription.substring(byIndex+5,taskDescription.length()-1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedDueDateTime + ")";
    }
}