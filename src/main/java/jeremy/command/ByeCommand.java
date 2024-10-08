package jeremy.command;

import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Does nothing when executed, as this command only signals the termination of the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * Returns {@code true} to indicate that this command will exit the application.
     *
     * @return {@code true}, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
