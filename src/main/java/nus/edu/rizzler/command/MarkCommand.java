package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "mark";

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param index The index of the task to mark as completed.
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the command by marking the task as completed and displaying a confirmation message.
     *
     * @param tasks The task list to modify.
     * @param userInterface The user interface for displaying the confirmation message.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.updateTaskStatus(this.getTaskIndex(), true);
        String message = "Good Job! Task marked. " + emoji.getPartyPopperEmoji();
        userInterface.displayMessage(String.format("%s:\n  %s", message, taskString));
    }
}
