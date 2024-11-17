package tars.command;

import tars.userinterface.UserInterface;
import tars.storage.Storage;
import tars.tasklist.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message to the user.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The user interface to display the goodbye message.
     * @param storage The storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    /**
     * Indicates that this command will cause the program to exit.
     *
     * @return True, indicating the program will exit after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
