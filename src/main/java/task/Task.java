package task;

import exceptions.IrisException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static exceptions.ExceptionErrorMessage.EMPTY_DESCRIPTION_MESSAGE;
import static exceptions.ExceptionErrorMessage.INVALID_DATE_FORMAT;

/**
 * The Task class represents a task with a description, status (whether it's done or not),
 * and an optional due date. It can parse user input to separate the task description
 * and date-related information.
 * <p>
 * This class is serializable to allow tasks to be saved to and loaded from files.
 * <p>
 * It also handles date parsing and ensures valid date formats.
 *
 * @author Tan Ping Hui
 */
public class Task implements Serializable {
    public String description;
    protected boolean isDone;
    public LocalDate dueDate;
    protected String timePreposition;

    /**
     * Constructs a Task object by separating the description from the date
     * in the provided details. The task is initialized as not done.
     *
     * @param details The user input containing the task description and due date.
     * @throws IrisException If the provided date format is invalid.
     */
    public Task(String details) throws IrisException {
        this.isDone = false;

        String[] descriptionAndTime = details.split("/", 2);
        String description = descriptionAndTime[0].trim();
        if (description.isEmpty()) {
            throw new IrisException(EMPTY_DESCRIPTION_MESSAGE);
        }
        this.description = description;

        if (descriptionAndTime.length == 1) {
            return;
        }
        String[] prepositionAndDate = descriptionAndTime[1].trim().split(" ", 2);
        this.timePreposition = prepositionAndDate[0].trim();

        if (prepositionAndDate.length == 1) {
            return;
        }
        String dateString = prepositionAndDate[1].trim();
        try {
            this.dueDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IrisException(INVALID_DATE_FORMAT);
        }
    }

    /**
     * Returns the status of the task in the form of a string.
     * "[X] " if the task is done, otherwise "[ ] ".
     *
     * @return A string representing whether the task is completed.
     */
    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns a string representation of the task, including its status,
     * description, and due date (if applicable).
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        String taskRepresentation = getStatus() + description;
        if (timePreposition != null && dueDate != null) {
            taskRepresentation += " (" + timePreposition + ": " + dueDate + ")";
        }
        return taskRepresentation;
    }

    /**
     * Marks the task as done or not done based on the provided status.
     *
     * @param status The new status of the task (true if done, false if not).
     */
    public void mark(boolean status) {
        isDone = status;
    }
}
