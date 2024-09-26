package bot.command;

import bot.Storage;
import bot.Ui;
import task.TaskList;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command will terminate the application.
     *
     * @return true, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}