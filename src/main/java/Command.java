/**
 * Represents an abstract command in the task management system.
 * This class serves as a base for all concrete command implementations.
 */
public abstract class Command {
    /**
     * Executes the command.
     * This method should be implemented by all concrete command classes to define
     * their specific behavior.
     *
     * @param tasks   The TaskList on which the command operates.
     * @param ui      The Ui object used to interact with the user.
     * @param storage The Storage object used to save and load tasks.
     * @throws PoirotException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PoirotException;

    /**
     * Checks if this command should exit the program.
     * By default, commands do not exit the program.
     *
     * @return true if the program should exit after this command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
