package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

/**
 * Represents a command to exit the application.
 * This command allows the user to terminate the program.
 */
public class ExitCommand extends Command {

    /** The command word for the exit command. */
    public static final String COMMAND_WORD = "bye";

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, indicating that this command terminates the program
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command.
     * This method does not perform any action as exiting the application
     * does not require modifying the task list or storage.
     *
     * @param tasks the TaskList (not used in this command)
     * @param ui the user interface for displaying messages (not used in this command)
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No action needed on exit
    }
}
