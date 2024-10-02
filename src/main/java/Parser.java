public class Parser {
    public static Command parse(String userCommand) throws AirBorderException {
        // Interpret the user's input command and return the corresponding Command object
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
        } else {
            // Throw an exception if the command is unrecognized
            throw new InvalidCommandException();
        }
    }
}
