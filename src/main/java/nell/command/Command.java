package nell.command;

import nell.list.TaskList;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected TaskList tasks;
    protected int taskIndex;

    /**
     * Constructor for a new Command object
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.taskIndex = -1;
    }

    /**
     * Constructor for child classes that handle specific task indexes
     */
    public Command(TaskList tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    /**
     * Handles the execution of a command
     */
    public abstract void execute();
}
