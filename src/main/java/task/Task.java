package task;

import exceptions.IrisException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    private static final String INVALID_DATE_FORMAT = "Invalid Date Format: Use YYYY-MM-DD format";

    protected String description;
    protected boolean isDone;
    public LocalDate dueDate;
    protected String timePreposition;

    public Task(String details) throws IrisException {
        this.isDone = false;

		String[] descriptionAndTime = details.split("/", 2);
		this.description = descriptionAndTime[0];

        if (descriptionAndTime.length == 1) {
            return;
        }
        String[] prepositionAndDate = descriptionAndTime[1].split(" ", 2);
        this.timePreposition = prepositionAndDate[0];

        if (prepositionAndDate.length == 1) {
            return;
        }
        String dateString = prepositionAndDate[1];
        try {
            this.dueDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IrisException(INVALID_DATE_FORMAT);
        }
    }

    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    @Override
    public String toString() {
        String taskRepresentation = getStatus() + description;
        if (timePreposition != null && dueDate != null) {
            taskRepresentation += "(" + timePreposition + ": " + dueDate + ")";
        }
        return taskRepresentation;
    }

    public void mark(boolean status) {
        isDone = status;
    }
}
