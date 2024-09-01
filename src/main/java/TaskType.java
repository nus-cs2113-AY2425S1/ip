public enum TaskType {
    LIST("list"), MARK("mark"), UNMARK("unmark"), EVENT("event"),
    TODO("todo"), DEADLINE("deadline"), BYE("bye"), INVALID("invalid");
    private final String command;

    TaskType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static TaskType fromCommand(String command) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getCommand().equalsIgnoreCase(command)) {
                return taskType;
            }
        }
        return INVALID;
    }
}
