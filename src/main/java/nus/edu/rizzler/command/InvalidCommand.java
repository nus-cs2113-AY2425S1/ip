package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class InvalidCommand extends Command {
    private Emoji emoji = new Emoji();

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = "I don't recognize the command" + emoji.getExclamationMarkEmoji();
        userInterface.displayMessage(message);
    }
}

