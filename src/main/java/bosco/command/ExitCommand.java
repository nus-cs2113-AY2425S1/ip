package bosco.command;

/**
 * Represents the command used to exit the command loop.
 */
public class ExitCommand extends Command {
    /**
     * Checks if the input command is the ExitCommand.
     *
     * @param command Command to be checked.
     * @return True if command is the ExitCommand, False otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
