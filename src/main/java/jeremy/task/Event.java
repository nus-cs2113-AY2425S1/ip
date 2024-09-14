package jeremy.task;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;

public class Event extends Task {
    protected final String from;
    protected final String to;

    public Event(String input) throws EmptyArgumentException, InvalidCommandFormatException {
        super(input.split("/", 3)[0].trim());

        if (input.isBlank()) {
            throw new EmptyArgumentException("Description cannot be empty");
        }

        String[] parts = input.split("/", 3);

        if (parts.length != 3) {
            throw new InvalidCommandFormatException("Event dates should come after \"/from \" and \"/to \"");
        }

        this.from = parts[1].trim().substring(5); // ignore "from "
        this.to = parts[2].trim().substring(3);   // ignore "to "
        this.icon = "E";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
