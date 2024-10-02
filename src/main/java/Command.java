/**
 * Abstract class representing a command in the Bebe application.
 * All command types such as AddCommand, DeleteCommand, and ExitCommand
 * should extend this class.
 */
public abstract class Command {

    /**
     * Executes the command. Each command type implements its own execution logic.
     *
     * @param tasks   The list of tasks where the command is executed.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to save or load tasks.
     * @throws BebeException if any error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException;

    /**
     * Returns whether this command will exit the program.
     *
     * @return true if the command is the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
