package aegis.command;

import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

/**
 * The ExitCommand class handles the termination of the Aegis application.
 * It displays a farewell message to the user and signals the application to exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a farewell message to the user.
     *
     * @param tasks   The TaskList being used (not affected by this command).
     * @param ui      The Ui used for interacting with the user.
     * @param storage The Storage used for task persistence (not affected by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println();
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, indicating the application should terminate after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
