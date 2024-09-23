package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static constants.Regex.DISPLAY_DATE_FORMAT;
import static constants.Regex.INPUT_DATE_FORMAT;

/**
 * Represents an Event in the Bento chatbot.
 * An Event has a name, a start date ("from"), and an end date ("to") represented by {@link LocalDate}.
 */
public class Event extends Task {
    private static final String COMMAND_FORMAT = "event %s /from %s /to %s";
    private final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
    private final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event with the specified task name, start date, and end date.
     *
     * @param taskName The name of the Event.
     * @param from     The start date of the Event.
     * @param to       The end date of the Event.
     */
    public Event(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the Event.
     *
     * @return The start date.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Sets a new start date for the Event.
     *
     * @param from The new start date.
     */
    public void setFrom(LocalDate from) {
        this.from = from;
    }

    /**
     * Returns the end date of the Event.
     *
     * @return The end date.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Sets a new end date for the Event.
     *
     * @param to The new end date.
     */
    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     * Returns the start date of the Event formatted for display.
     *
     * @return The formatted start date.
     */
    public String getFromAsString() {
        return displayFormatter.format(from);
    }

    /**
     * Returns the end date of the Event formatted for display.
     *
     * @return The formatted end date.
     */
    public String getToAsString() {
        return displayFormatter.format(to);
    }

    /**
     * Returns the start date of the Event in a command-friendly format, typically used for saving.
     *
     * @return The start date formatted for saving as a command.
     */
    public String getFromAsCommand() {
        return saveFormatter.format(from);
    }

    /**
     * Returns the end date of the Event in a command-friendly format, typically used for saving.
     *
     * @return The end date formatted for saving as a command.
     */
    public String getToAsCommand() {
        return saveFormatter.format(to);
    }

    /**
     * Returns a string representation of the Event.
     * The format includes the Event type indicator "[E]" followed by the task's done marker,
     * task name, start date, and end date in a human-readable format.
     *
     * @return A string representing the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFromAsString(), getToAsString());
    }

    /**
     * Returns the command string to recreate the Event.
     * This is used for saving the Event in a format that can be reloaded later.
     *
     * @return The task as a command string.
     */
    @Override
    public String getTaskAsCommand() {
        return String.format(COMMAND_FORMAT, getTaskName(), getFromAsCommand(), getToAsCommand());
    }
}
