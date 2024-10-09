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

    /**
     * Returns the start date of the event in the format "dd MMM yyyy", which is suitable for display.
     * 
     * @return The start date of the event in the format "dd MMM yyyy". 
     */
    public String getDateFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns the start date of the event in the format "yyyy-MM-dd", which is suitable for saving to a file.
     * 
     * @return The start date of the event in the format "yyyy-MM-dd". 
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the end date of the event in the format "yyyy-MM-dd", which is suitable for saving to a file.
     * 
     * @return The end date of the event in the format "yyyy-MM-dd". 
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the end date of the event in the format "dd MMM yyyy", which is suitable for display.
     * 
     * @return The end date of the event in the format "dd MMM yyyy". 
     */
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
