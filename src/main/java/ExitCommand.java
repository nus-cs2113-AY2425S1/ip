/**
 * Represents a command to exit the Bebe application.
 * When executed, this command will terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, showing a goodbye message to the user.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The UI object to display the goodbye message.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true to indicate that this command will exit the program.
     *
     * @return true, as this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
