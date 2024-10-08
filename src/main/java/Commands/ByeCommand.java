package Commands;

/**
 * Represents a command to exit the program.
 * The command word for this command is "bye".
 * 
 * <p>Usage:</p>
 * <pre>
 * bye: Exits the program.
 * Example: bye
 * </pre>
 * 
 * <p>When executed, this command returns a message indicating that the program is exiting.</p>
 * 
 * <p>This command can be checked if it is an exit command using the {@link #isExit(Command)} method.</p>
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "\tExample: " + COMMAND_WORD;

    /**
     * Executes the ByeCommand which signals the end of the program.
     *
     * @return CommandResult containing the farewell message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(
            "Bye. Hope to see you again soon!"
        );
    }

    /**
     * Checks if the given command is an instance of ByeCommand.
     *
     * @param command The command to check.
     * @return true if the command is an instance of ByeCommand, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}
