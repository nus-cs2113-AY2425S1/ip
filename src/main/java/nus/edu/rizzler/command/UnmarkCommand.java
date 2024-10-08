package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class UnmarkCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(int index) {
        super(index);
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.updateTaskStatus(this.getTaskIndex(), false);
        String message = "No worries! Task unmarked. " + emoji.getReverseEmoji();
        userInterface.displayMessage(String.format("%s:\n  %s", message, taskString));
    }
}

