package task;

import exceptions.IrisException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    private static final String INVALID_DATE_FORMAT = "Invalid Date Format: Use YYYY-MM-DD format";

    public String description;
    protected boolean isDone;
    public LocalDate dueDate;
    protected String timePreposition;

    /**
     * Separates description from the dates in details and initialise accordingly
     * @param details is the user input without command
     */
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

    /**
     * Getter for task status
     * @return a string that represents whether the task is done or not
     */
    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * @return a string representation of task
     */
    @Override
    public String toString() {
        String taskRepresentation = getStatus() + description;
        if (timePreposition != null && dueDate != null) {
            taskRepresentation += "(" + timePreposition + ": " + dueDate + ")";
        }
        return taskRepresentation;
    }

    /**
     * Setter for isDone
     * @param status is the current task status
     */
    public void mark(boolean status) {
        isDone = status;
    }
}
