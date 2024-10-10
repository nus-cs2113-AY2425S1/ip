/**
 * The Command abstract class represents a user command.
 * Concrete command classes extend this class to implement specific commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for saving/loading tasks.
     * @throws AirBorderException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AirBorderException;

    /**
     * Indicates whether this command exits the application.
     *
     * @return true if the command exits the application; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
