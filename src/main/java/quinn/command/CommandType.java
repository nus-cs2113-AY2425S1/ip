package quinn.command;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String label;

    CommandType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
