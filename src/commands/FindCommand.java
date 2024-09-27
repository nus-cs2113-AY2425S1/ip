package commands;

import exception.IncompleteCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

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
