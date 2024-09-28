package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents a general command in the task management system.
 * This class serves as a base for all specific command types,
 * providing a common structure and methods for task execution.
 */
public abstract class Command {

    /** The index of the task associated with the command. */
    private int taskIndex = -1;

    /**
     * Default constructor for Command.
     */
    public Command() {}

    /**
     * Constructs a Command with the specified task index.
     *
     * @param taskIndex the index of the task associated with this command
     */
    public Command(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Retrieves the index of the task associated with this command.
     *
     * @return the index of the task
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return false, indicating this command does not exit the program
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, performing the necessary actions on the task list.
     *
     * @param tasks the TaskList to operate on
     * @param ui the user interface for displaying messages
     * @param storage the storage system for saving task data
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
