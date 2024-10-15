package main.java;

/**
 * Parses user input and creates the matching command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param userInput The input command string from the user.
     * @return The Command object corresponding to the user input.
     * @throws KenChatException If the input is invalid or commands are wrong.
     */
    public static Command parse(String userInput) throws KenChatException {
        userInput = userInput.trim();

        if (userInput.isEmpty()) { // Check for empty command
            throw new KenChatException(KenChatException.emptyCommand());
        }

        if (userInput.contains("  ")) { // Check for multiple spaces
            throw new KenChatException(KenChatException.multipleSpaces());
        }

        String[] command = userInput.split(" ", 2);
        String action = command[0].toLowerCase();
        String arguments = command.length > 1 ? command[1] : "";

        switch (action) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("mark"));
            }
            try {
                return new MarkCommand(Integer.parseInt(arguments));
            } catch (NumberFormatException e) {
                throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
            }
        case "unmark":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("mark"));
            }
            try {
                return new UnmarkCommand(Integer.parseInt(arguments));
            } catch (NumberFormatException e) {
                throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
            }
        case "delete":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("mark"));
            }
            try {
                return new DeleteCommand(Integer.parseInt(arguments));
            } catch (NumberFormatException e) {
                throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
            }
        case "todo":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyDescriptionMessage(action));
            }
            return new AddCommand(new Task.ToDo(arguments));
        case "deadline":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyDescriptionMessage(action));
            }
            String[] deadlineParts = arguments.split(" /by ", 2);
            if (deadlineParts.length == 2) {
                return new AddCommand(new Task.Deadline(deadlineParts[0], deadlineParts[1]));
            } else {
                throw new KenChatException(KenChatException.getInvalidDeadlineFormatMessage());
            }
        case "event":
            if (arguments.isEmpty()) {
                throw new KenChatException(KenChatException.getEmptyDescriptionMessage(action));
            }
            String[] eventParts = arguments.split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                return new AddCommand(new Task.Event(eventParts[0], eventParts[1], eventParts[2]));
            } else {
                throw new KenChatException(KenChatException.getInvalidEventFormatMessage());
            }
        case "help":
            return new HelpCommand();
        default:
            throw new KenChatException(KenChatException.getUnknownCommandMessage());
        }
    }
}
