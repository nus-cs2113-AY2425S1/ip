public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command parseString(String command) {
        for (Command c : Command.values()) {
            if (c.getCommand().equals(command)) {
                return c;
            }
        }
        return null;
    }
}