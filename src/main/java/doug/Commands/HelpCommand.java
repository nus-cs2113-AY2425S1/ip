package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;

/**
 * Represents the actions of the Help command
 * Lists out all the commands currently available in Doug, and their formats/uses
 */
public class HelpCommand extends Command{

    public HelpCommand() {

    }

    /**
     * Lists all the available commands and their formats/uses
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printListOfCommands();
    }

}
