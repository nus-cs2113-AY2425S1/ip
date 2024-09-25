package jeremy.command;

import jeremy.exception.IllegalCommandException;

public enum Command {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    BYE;

    public static Command fromString(String command) throws IllegalCommandException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException(command);
        }
    }

    public static Command fromIcon(String icon) throws IllegalCommandException {
        switch (icon) {
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        case "E":
            return EVENT;
        default:
            throw new IllegalCommandException("Corrupted storage :(");
        }
    }

    public boolean isExit() {
        return this == BYE;
    }
}
