package commands;
import exception.IllegalCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

/**
 * The ListCommand class handles the "list" command, which shows all tasks in the task list.
 */
public class ListCommand extends Command {
    private final String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the list command by calling the UI to display all tasks in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] commandPieces = userInput.split(SPACE);
        try{
            if (commandPieces.length >1){
                throw new IllegalCommandException(ILLEGAL_COMMAND_MESSAGE + "\n" + SEPARATOR);
            }
            ui.showTaskListed(tasks);
        } catch (IllegalCommandException e){
            ui.showLine();
            ui.print(e.getMessage());
        }
    }
}

