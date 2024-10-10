package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Face;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to unmark a task, indicating it is not completed.
 */
public class UnmarkCommand extends Command {
    private Face face = new Face();
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        super(index);
    }


    /**
     * Executes the command by unmarking the task as not completed and displaying a confirmation message.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying the confirmation message.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.updateTaskStatus(this.getTaskIndex(), false);
        String message = String.format("Good Job %s! Task unmarked. ", face.getBlushingFace());
        userInterface.displayMessage(String.format("%s:\n  %s", message, taskString));
    }
}

