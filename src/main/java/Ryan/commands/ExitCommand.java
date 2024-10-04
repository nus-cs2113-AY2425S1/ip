package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printGoodbye();
    }

    /**
     * Returns true to indicate that this command exits the application.
     *
     * @return true, indicating the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
