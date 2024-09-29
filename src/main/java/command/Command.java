package command;

import tasklist.TaskList;

public abstract class Command {
    protected static final String SEPARATOR = "_".repeat(30);

    /**
     * Executes the specific command for the task.
     *
     * @param taskList  The list of tasks.
     * @param userInput The user input that describes the command.
     */
    public abstract void execute(TaskList taskList, String userInput);
}
