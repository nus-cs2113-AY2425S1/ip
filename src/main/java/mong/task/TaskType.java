package mong.task;

import mong.exception.IllegalTaskTypeException;

public enum TaskType {
    LIST("list"), MARK("mark"), UNMARK("unmark"), EVENT("event"),
    TODO("todo"), DEADLINE("deadline"), BYE("bye"), DELETE ("delete"),
    INVALID("invalid");
    private final String command;

    TaskType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static TaskType fromCommand(String command) throws IllegalTaskTypeException {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getCommand().equalsIgnoreCase(command)) {
                return taskType;
            }
        }
        throw new IllegalTaskTypeException("Not a valid command: " + command);
    }
}
