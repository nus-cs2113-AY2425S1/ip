/**
 * Represents a task with a description and completion status (done or not done).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * By default, the task is marked as not done.
     *
     * @param description A description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;  // By default, a task is not done when created
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and " " indicates that it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
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
     * Returns a string representation of the task,
     * including its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

/**
 * Represents a ToDo task, which is a task without any specific date or time.
 */
class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description A description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Represents a Deadline task, which is a task that needs to be done
 * before a specific date/time.
 */
class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description A description of the task.
     * @param by The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * Represents an Event task, which is a task that starts and ends
 * at specific dates/times.
 */
class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Event task with the given description, start, and end times.
     *
     * @param description A description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}