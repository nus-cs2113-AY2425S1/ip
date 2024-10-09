package chattycharlie.task;

import chattycharlie.CharlieExceptions;
import chattycharlie.commands.CommandType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start and end date and time.
 * An <code>Event</code> task has a description, a start date and time, and an end date and time.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructs an <code>Event</code> task with the specified description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param start the start date and time in <code>yyyy-MM-dd HH:mm</code> format.
     * @param end the end date and time in <code>yyyy-MM-dd HH:mm</code> format.
     */
    public Event(String description, String start, String end) throws CharlieExceptions {
        super(description, CommandType.EVENT);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            String[] startParts = start.split(" ");
            String[] endParts = end.split(" ");

            if (startParts.length != 2 || endParts.length != 2) {
                throw CharlieExceptions.invalidFormat("Invalid event format. Expected format: 'yyyy-MM-dd HH:mm'");
            }

            if (!startParts[1].matches("\\d{2}:\\d{2}") || !endParts[1].matches("\\d{2}:\\d{2}")) {
                throw CharlieExceptions.invalidFormat("HH:mm (e.g., 14:00)");
            }

            this.startDate = LocalDate.parse(startParts[0], dateFormatter);
            this.startTime = LocalTime.parse(startParts[1], timeFormatter);
            this.endDate = LocalDate.parse(endParts[0], dateFormatter);
            this.endTime = LocalTime.parse(endParts[1], timeFormatter);
        } catch (CharlieExceptions e) {
            throw CharlieExceptions.invalidFormat("event DESCRIPTION from START_DATE START_TIME " +
                    "to END_DATE END_TIME");
        }
    }

    /**
     * Gets the start date of the event task.
     *
     * @return the start date as a <code>LocalDate</code>.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns a string representation of the event task, including its marked status,
     * description, start date and time, and end date and time.
     *
     * @return a string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " "
                + startTime + " to: " + endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " "
                + endTime + ")";
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving.
     *
     * @return a string representation of the event task formatted for saving.
     */
    @Override
    public String toSaveFormat() {
        return "[E]" + super.toSaveFormat() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + startTime + " to: " + endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + endTime + ")";
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
