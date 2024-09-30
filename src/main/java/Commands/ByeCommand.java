package Commands;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
            "Bye. Hope to see you again soon!"
        );
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}
