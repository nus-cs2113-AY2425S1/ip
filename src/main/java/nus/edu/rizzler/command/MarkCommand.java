package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class MarkCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(int index) {
        super(index);
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = tasks.updateTaskStatus(this.getTaskIndex(), true);
        String message = "Good Job! Task marked. " + emoji.getPartyPopperEmoji();
        userInterface.displayMessage(String.format("%s:\n  %s", message, taskString));
    }
}
