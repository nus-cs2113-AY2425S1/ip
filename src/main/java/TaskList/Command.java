package TaskList;

import Task.TaskList;

public abstract class Command {
    protected static final String SEPARATOR = "_".repeat(30);
    public abstract void execute(TaskList taskList, String userInput);
}
