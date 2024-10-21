package commands;

import exception.IncompleteCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

/**
 * The FindCommand class allows the user to search for tasks in the TaskList that contain a keyword.
 */
public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the find command, which searches for tasks that contain the specified keyword.
     * It first parses the user input to extract the keyword, then uses the UI to display the matching tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException {
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String keyWord = commandContent.replace(commandPrefix, EMPTY).trim();

        try {
            if (keyWord.isEmpty()) { // input is only mark or unmark
                throw new IncompleteCommandException("The keyword");
            }
            ui.showTaskFound(tasks, keyWord);
        } catch (IncompleteCommandException e){
            ui.showLine();
            ui.print(e.getMessage());
            ui.showLine();
        }
    }
}
