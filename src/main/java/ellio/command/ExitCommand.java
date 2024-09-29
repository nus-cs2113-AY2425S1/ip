package ellio.command;

import ellio.BotText;
import ellio.storage.Storage;
import ellio.task.Task;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String InputCommand) {
        super(InputCommand);
    }

    /**
     * Sets exit flag to true, for program termination
     * Closes Scanner to stop reading user input
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        setExit();
        ui.closeScanner();
    }
}
