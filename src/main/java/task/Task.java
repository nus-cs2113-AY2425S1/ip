package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task that can be added to a task list.
 *
 * This class encapsulates the task description, completion status, and provides methods
 * to manipulate and retrieve task details. It also provides methods for formatting task data
 * for display and storage purposes.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    // Formatters for different date formats
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter ALTERNATE_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor for the Task class.
     * Initializes a new task with the provided description and marks it as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the due date of the task.
     * This method is meant to be overridden by subclasses like Deadline or Event.
     *
     * @return A default LocalDateTime value (January 1st, 1970).
     */
    public LocalDateTime getDueDate() {
        return LocalDateTime.of(1970, 1, 1, 0, 0);  // Default placeholder date
    }

    /**
     * Returns the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon for the task.
     *
     * "X" indicates that the task is done, and " " (empty) indicates that it is not done.
     *
     * @return The status icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a format suitable for file storage.
     * This includes the task type, completion status, and the description.
     *
     * @return A string representing the task in file storage format.
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }

    /**
     * Returns the task type.
     * This method can be overridden by subclasses to return specific types like "D" for Deadline or "E" for Event.
     *
     * @return The task type, default is "T" for Task.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Returns a string representation of the task for display.
     * The string includes the status icon and task description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
