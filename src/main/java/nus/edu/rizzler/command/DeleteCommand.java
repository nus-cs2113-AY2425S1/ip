package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Face;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private Face face = new Face();
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Executes the command by deleting the task from the task list,
     * displaying a confirmation message, and updating storage if needed.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.deleteTask(this.getTaskIndex());
        int taskSize = tasks.getSize();

        String message = String.format("Task deleted:%n  %s%n" +
                "Now you have %d tasks in the list.", taskString, taskSize);
        userInterface.displayMessage(message);
    }
}

