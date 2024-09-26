package quinn.command;

/**
 * Enumerates the types of commands available in the task management system.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    private final String label;


    /**
     * Constructs a CommandType with the specified label.
     *
     * @param label the string representation of the command
     */
    CommandType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
