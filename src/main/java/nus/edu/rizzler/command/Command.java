package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents an abstract command to be executed within the Rizzler application.
 * Specific command types should extend this class and implement the {@code execute} method.
 */
public abstract class Command {
    private int taskIndex = -1;

    /**
     * Constructs a {@code Command} with no specific task index.
     */
    public Command() {}

    /**
     * Constructs a {@code Command} with the specified task index.
     *
     * @param taskIndex The index of the task related to the command.
     */
    public Command(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Returns the index of the task associated with this command.
     *
     * @return The task index, or -1 if no task index is associated.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code true} if the command causes the application to exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, interacting with the task list, user interface, and storage as needed.
     *
     * @param tasks The task list to modify or query.
     * @param userInterface The user interface for displaying messages to the user.
     * @param storage The storage to read from or write to as needed.
     */
    public abstract void execute(TaskList tasks, UserInterface userInterface, Storage storage);
}

