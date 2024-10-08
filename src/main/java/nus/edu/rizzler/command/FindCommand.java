package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class FindCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = String.format("Matching tasks %s:%n%s",
                emoji.getCoolFaceEmoji(), tasks.findKeyword(keyword));
        userInterface.displayMessage(message);
    }
}
