package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected final LocalDate by;

    public Deadline(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 2)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Deadline description cannot be empty");
        }

        String[] parts = input.split("/", 2);

        if (parts.length != 2 || parts[1].isBlank()) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Deadline dates should come after \"/by \"");
        }

        String datePart = parts[1].trim();
        if (!datePart.startsWith("by ")) {
            throw new InvalidCommandFormatException("Deadline dates should start with \"/by \"");
        }

        try {
            this.by = LocalDate.parse(datePart.substring(3)); // ignore "bye "
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Dates should be in the format yyyy-mm-dd, eg 2020-03-21");
        }
        this.icon = "D";
    }

    public Deadline(String input, boolean isDone) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 2)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Deadline description cannot be empty");
        }

        String[] parts = input.split("/", 2);

        if (parts.length != 2 || parts[1].isBlank()) {
            throw new InvalidCommandFormatException("Invalid command format, " +
                    "Deadline dates should come after \"/by \"");
        }

        String datePart = parts[1].trim();
        if (!datePart.startsWith("by ")) {
            throw new InvalidCommandFormatException("Deadline dates should start with \"/by \"");
        }
        this.isDone = isDone;
        try {
            this.by = LocalDate.parse(datePart.substring(3)); // ignore "bye "
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Dates should be in the format yyyy-mm-dd, eg 2020-03-21");
        }
        this.icon = "D";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description
                + " | " + by;
    }
}
