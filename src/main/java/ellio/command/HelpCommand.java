package ellio.command;

import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Prints Custom Help Message
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showHelpMessage();
    }
}
