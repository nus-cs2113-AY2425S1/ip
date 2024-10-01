package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected final LocalDate from;
    protected final LocalDate to;

    public Event(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 3)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Event description cannot be empty");
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
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Dates should be in the format yyyy-mm-dd, eg 2020-03-21");
        }
        this.icon = "E";
    }

    public Event(String input, boolean isDone) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 3)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Event description cannot be empty");
        }

        String[] parts = input.split("/", 3);

        if (parts.length != 3) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Event dates should come after \"/from \" and \"/to \"");
        }

        this.isDone = isDone;
        String from = parts[1].trim().substring(5); // ignore "from "
        String to = parts[2].trim().substring(3); // ignore "to "
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Dates should be in the format yyyy-mm-dd, eg 2020-03-21");
        }
        this.icon = "E";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description
                + " | " + from + " | " + to;
    }
}
