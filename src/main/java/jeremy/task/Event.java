package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specified time frame.
 * The task includes a description, a start time, and an end time,
 * which are specified after "/from" and "/to", respectively.
 */
public class Event extends Task {
    protected final LocalDate startDate;
    protected final LocalDate endDate;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param input The task description followed by the time frame in the format "description /from start /to end".
     * @throws EmptyArgumentException If the task description is empty or blank.
     * @throws InvalidCommandFormatException If the command format is invalid (i.e., if the time frame is not properly formatted after "/from" and "/to").
     */
    public Event(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 3)[0].trim(), "E");

        if (input.isBlank()) {
            throw new EmptyArgumentException("Event description");
        }

        String[] parts = input.split("/", 3);

        if (parts.length != 3 || parts[1].isBlank() || parts[2].isBlank()) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Event dates should come after \"/from \" and \"/to \"");
        }

        String dateFrom = parts[1].trim();
        String dateTo = parts[2].trim();
        if (!dateFrom.startsWith("from ") || !dateTo.startsWith("to ")) {
            throw new InvalidCommandFormatException("Event dates should start with \"from \" or \"to \"");
        }

        String from = parts[1].trim().substring(5); // ignore "from "
        String to = parts[2].trim().substring(3); // ignore "to "
        try {
            this.startDate = LocalDate.parse(from);
            this.endDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Dates should be in the format yyyy-mm-dd, eg 2020-03-21");
        }
    }

    /**
     * Returns a string representation of the event, including its completion status and time frame.
     *
     * @return A string in the format "[E][X] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the event formatted for storage.
     *
     * @return A string in the format "E | 0 | description | from | to", where the completion status is 0 or 1.
     */
    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description
                + " | " + this.startDate + " | " + this.endDate;
    }
}
