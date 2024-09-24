package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

public class Deadline extends Task {
    protected final String by;

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

        this.by = datePart.substring(3); // ignore "by "
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
        this.by = datePart.substring(3); // ignore "by "
        this.icon = "D";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description
                + " | " + by;
    }
}
