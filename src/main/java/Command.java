public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT;

    public static Command fromString(String command) throws IllegalCommandException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException("Unrecognised command");
        }
    }
}
