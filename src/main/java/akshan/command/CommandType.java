package akshan.command;

/**
 * Enum representing different command types for the task management system.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String command;

    /**
     * Constructs a CommandType with the specified command string.
     *
     * @param command The string representation of the command.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The command string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Converts a string to the corresponding CommandType.
     *
     * @param text The string to convert.
     * @return The corresponding CommandType.
     * @throws IllegalArgumentException If no matching CommandType is found.
     */
    public static CommandType fromString(String text) throws IllegalArgumentException {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Command text cannot be null or empty");
        }
        for (CommandType b : CommandType.values()) {
            if (b.command.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No command with text '" + text + "' found");
    }
}