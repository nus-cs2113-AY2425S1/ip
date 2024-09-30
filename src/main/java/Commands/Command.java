package Commands;

import TasksList.TasksList;

public class Command {
    protected TasksList tasksList;
    public static final String COMMAND_WORD = "add";

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
