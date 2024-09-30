package Commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
            "List of commands:\n"
            + ListCommand.MESSAGE_USAGE
            + "\n" + HelpCommand.MESSAGE_USAGE
            + "\n" + TodoCommand.MESSAGE_USAGE
        );
    }
}
