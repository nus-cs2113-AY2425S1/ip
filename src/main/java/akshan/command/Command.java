package akshan.command;

import akshan.task.TaskList;

import java.io.IOException;

public abstract class Command {
    protected final CommandType commandType;
    protected final String taskString;
    protected final TaskList taskList;

    /**
     * Constructor for Command.
     *
     * @param commandType The command from the user.
     * @param taskString The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public Command(CommandType commandType, String taskString, TaskList taskList) {
        this.commandType = commandType;
        this.taskString = taskString;
        this.taskList = taskList;
    }

    /**
     * Runs command based on user input.
     *
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    public abstract void execute() throws IllegalArgumentException;
}
