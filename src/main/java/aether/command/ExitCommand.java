package aether.command;

import aether.storage.Storage;
import aether.tasklist.TaskList;
import aether.ui.Ui;

/**
 * Handles the "bye" command to exit the application.
 * <p>
 * This command triggers the termination of the application by displaying the end screen
 * to the user.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying the end screen to the user.
     *
     * @param taskList The {@code TaskList} object containing all current tasks.
     * @param ui       The {@code Ui} object responsible for user interactions.
     * @param storage  The {@code Storage} object responsible for saving and loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.showEndScreen();
    }

    /**
     * Determines whether this command should trigger the application to exit.
     *
     * @return {@code true} to indicate that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
