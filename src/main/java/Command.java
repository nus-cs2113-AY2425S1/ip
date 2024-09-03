public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN_COMMAND; // for unrecognised commands

    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN_COMMAND;
        }
    }
}
