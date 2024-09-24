package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

/**
 * Represents a command to exit the application.
 * The <code>ExitCommand</code> sets the exit flag to true, terminating the Jeff.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with default settings.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the ExitCommand, setting the exit flag to true and displaying the exit message via the UI.
     *
     * @param tasks   The TaskList containing the tasks (unused in this command).
     * @param ui      The Ui for user interaction, which displays the exit message.
     * @param storage The Storage object for handling task storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.showExit();
    }
}
