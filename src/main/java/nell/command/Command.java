package nell.command;

import nell.TaskList;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected String commandWord;
    protected TaskList tasks;
    protected int taskIndex;

    /**
     * Constructor for a new Command object
     */
    public Command(String commandWord, TaskList tasks) {
        this.commandWord = commandWord;
        this.tasks = tasks;
        this.taskIndex = -1;
    }

    /**
     * Constructor for child classes that handle specific task indexes
     */
    public Command(String commandWord, TaskList tasks, int taskIndex) {
        this.commandWord = commandWord;
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    /**
     * Handles the execution of a command
     */
    public abstract void execute();
}
