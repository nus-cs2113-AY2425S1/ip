/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     *
     * @param tasks  The task list (not used).
     * @param ui     The user interface for displaying messages to the user.
     * @param storage The storage for saving tasks to a file (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
    /**
     * Indicates whether this command is an exit command.
     *
     * @return true, since this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
