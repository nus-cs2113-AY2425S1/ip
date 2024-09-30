package Commands;

/**
 * Represents a command to display help information for the program.
 * The HelpCommand provides a list of available commands and their usage instructions.
 * This command can be executed to guide users on how to use the program effectively.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    /**
     * Executes the HelpCommand and returns a CommandResult containing the list of available commands and their usage.
     *
     * @return CommandResult containing the list of commands and their usage.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(
            "List of commands:\n"
            + ByeCommand.MESSAGE_USAGE
            + "\n" + HelpCommand.MESSAGE_USAGE
            + "\n" + ListCommand.MESSAGE_USAGE
            + "\n" + TodoCommand.MESSAGE_USAGE
            + "\n" + DeadlineCommand.MESSAGE_USAGE
            + "\n" + EventCommand.MESSAGE_USAGE
            + "\n" + MarkCommand.MESSAGE_USAGE
            + "\n" + UnmarkCommand.MESSAGE_USAGE
            + "\n" + DeleteCommand.MESSAGE_USAGE
        );
    }
}
