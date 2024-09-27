package esme.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, which is a task that has a start and end date.
 */
public class Event extends Task {
    /**
     * The start date of the event.
     */
    private LocalDate from;

    /**
     * The end date of the event.
     */
    private LocalDate to;

    /**
     * The task type of the event, which is "event".
     */
    private String taskType;

    /**
     * Creates a new event task with the given description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "event";
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getLocalDateFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDate getLocalDateTo() {
        return this.to;
    }

    public String getDateFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDateTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns a string representation of the event task, which is in the format of
     * [E][] [description] (from: [start date], to: [end date]).
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateFrom() + ", to: " + getDateTo() + ")";
    }
}
