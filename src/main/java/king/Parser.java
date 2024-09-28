package king;

import king.command.*;

/**
 * The parser class is responsible for parsing the user input and
 * returning the appropriate command based on the parsed input.
 */
public class Parser {

    /**
     * Parses the user input string and returns the command.
     *
     * @param userInput The input string provided by the user.
     * @return The corresponding Command object based on the user input.
     * @throws KingException If the command is not recognized or the input format is invalid.
     */
    protected static Command parse(String userInput) throws KingException {
        String parsedInput = userInput.trim().toLowerCase();
        userInput = userInput.trim();

        if (parsedInput.equals("bye")) {
            return new ExitCommand();
        } else if (parsedInput.equals("list")) {
            return new ListCommand();
        } else if (parsedInput.startsWith("mark")) {
            return new MarkCommand(parseTaskIndex(userInput));
        } else if (parsedInput.startsWith("unmark")) {
            return new UnmarkCommand(parseTaskIndex(userInput));
        } else if (parsedInput.startsWith("todo")) {
            return new TodoCommand(userInput);
        } else if (parsedInput.startsWith("deadline")) {
            return new DeadlineCommand(userInput);
        } else if (parsedInput.startsWith("event")) {
            return new EventCommand(userInput);
        } else if (parsedInput.startsWith("delete/exit")) {
            return new DeleteExitCommand();
        } else if (parsedInput.startsWith("delete")) {
            return new DeleteCommand(parseTaskIndex(userInput));
        } else if (parsedInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else {
            throw new KingException("Unknown command. Please try again.\n");
        }
    }

    /**
     * Extracts and parses the task index from the user's input.
     * This method is used for commands that require a task number, such as 'mark', 'unmark', or 'delete'.
     *
     * @param text The input string containing the command and task number.
     * @return The zero-based index of the task extracted from the input.
     * @throws KingException If the task number is missing or cannot be parsed.
     */
    protected static int parseTaskIndex(String text) throws KingException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new KingException("Your input can only be numbers!\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KingException("Have u forgotten to input the task number?\n");
        }

        return taskIndex;
    }
}
