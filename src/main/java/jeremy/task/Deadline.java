package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

public class Deadline extends Task {
    protected final String by;

    public Deadline(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 2)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Description cannot be empty");
        }

        String[] parts = input.split("/", 2);

        if (parts.length != 2) {
            throw new InvalidCommandFormatException("Deadline dates should come after \"/by \"");
        }

        this.by = parts[1].trim().substring(3); // ignore "by "
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
