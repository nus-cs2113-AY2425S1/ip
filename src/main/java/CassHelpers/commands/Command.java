package CassHelpers.commands;
/**
 * The Command interface represents an abstraction for various commands
 * that can be executed on the task list. Each command class implementing
 * this interface should provide its own implementation of the execute method.
 */
public interface Command {
    /**
     * Executes the specific command action.
     * Implementing classes must define the behavior of the command.
     */
    public void execute();
}
