package Commands;

import TasksList.TasksList;

/**
 * Represents a command that can be executed.
 * Each command operates on a TasksList and returns a CommandResult.
 */
public class Command {
    protected TasksList tasksList;
    public static final String COMMAND_WORD = "add";

    /**
     * Executes the command and returns the result.
     *
     * @return a CommandResult object containing the result of the command execution.
     */
    public CommandResult execute() {
        return new CommandResult("sample command");
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TasksList tasksList) {
        this.tasksList = tasksList;
    }

}
