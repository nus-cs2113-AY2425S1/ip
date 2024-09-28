package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

/**
 * Represents a command to display help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the help command by displaying help information through the UI.
     *
     * @param tasks Unused parameter.
     * @param ui The user interface for displaying help.
     * @param storage Unused parameter.
     * @throws AlyException If any error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlyException {
        ui.help();
    }
}