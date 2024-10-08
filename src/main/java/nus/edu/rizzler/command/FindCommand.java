package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

/**
 * Represents a command to find tasks that match a specified keyword.
 */
public class FindCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "find";

    private String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding and displaying tasks that match the keyword.
     *
     * @param tasks The task list to search.
     * @param userInterface The user interface for displaying the search results.
     * @param storage The storage for task data (not used in this command).
     */
    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String message = String.format("Matching tasks %s:%n%s",
                emoji.getCoolFaceEmoji(), tasks.findKeyword(keyword));
        userInterface.displayMessage(message);
    }
}
