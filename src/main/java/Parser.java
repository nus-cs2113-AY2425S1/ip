/**
 * The {@code Parser} class is responsible for interpreting user commands
 * and returning the appropriate {@code Command} objects to be executed.
 */
public class Parser {

    /**
     * Parses the user's input command and returns the corresponding {@code Command} object.
     *
     * @param userCommand The full command entered by the user.
     * @return The {@code Command} object representing the user's request.
     * @throws AirBorderException If the user enters an unrecognized command.
     */
    public static Command parse(String userCommand) throws AirBorderException {
        if (userCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (userCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userCommand.startsWith("todo ")) {
            return new AddCommand("todo", userCommand.substring(5).trim());
        } else if (userCommand.startsWith("deadline ")) {
            return new AddCommand("deadline", userCommand.substring(9).trim());
        } else if (userCommand.startsWith("event ")) {
            return new AddCommand("event", userCommand.substring(6).trim());
        } else if (userCommand.startsWith("delete ")) {
            return new DeleteCommand(userCommand.substring(7).trim());
        } else if (userCommand.startsWith("mark ")) {
            return new MarkCommand(userCommand.substring(5).trim(), true);
        } else if (userCommand.startsWith("unmark ")) {
            return new MarkCommand(userCommand.substring(7).trim(), false);
        } else if (userCommand.startsWith("find ")) {
            return new FindCommand(userCommand.substring(5).trim());
        } else {
            throw new InvalidCommandException();
        }
    }
}
