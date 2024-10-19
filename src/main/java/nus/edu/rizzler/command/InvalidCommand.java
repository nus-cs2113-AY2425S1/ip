package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Face;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command for unrecognized input, signaling an invalid command.
 */
public class InvalidCommand extends Command {
    private Face face = new Face();

    /**
     * Executes the command by displaying an error message for invalid input.
     *
     * @param tasks The task list (not used in this command).
     * @param userInterface The user interface for displaying the error message.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = "I don't recognize the command! " + face.getConfusedFace() + "?";
        userInterface.displayMessage(message);
    }
}

