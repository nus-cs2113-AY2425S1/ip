package wildpeace.task;

import wildpeace.exceptions.EmptyCommandException;

/**
 * Parses user input into commands and arguments. Responsible for splitting user input
 * into command and arguments and handling empty input cases.
 */
public class Parser {

    /**
     * Parses the user's input into command and argument components.
     * The input is split into a command and its arguments. If the input is empty,
     * an exception is thrown.
     *
     * @param input The user's input string.
     * @return A string array where the first element is the command and the second is the argument (if any).
     * @throws EmptyCommandException If the input is empty or consists of only whitespace.
     */
    public String[] parse(String input) throws EmptyCommandException {
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            throw new EmptyCommandException("Input cannot be empty.");
        }
        return trimmedInput.split("\\s+", 2);
    }

    /**
     * Extracts the command from the parsed input.
     * The command is the first word of the user's input.
     *
     * @param parsedInput The parsed input array (result from the parse method).
     * @return The command in lowercase form.
     */
    public String getCommand(String[] parsedInput) {
        return parsedInput[0].toLowerCase();
    }

    /**
     * Extracts the arguments from the parsed input.
     * The arguments are the part of the input that follows the command.
     *
     * @param parsedInput The parsed input array (result from the parse method).
     * @return The arguments as a string, or an empty string if no arguments were provided.
     */
    public String getArguments(String[] parsedInput) {
        return parsedInput.length > 1 ? parsedInput[1] : "";
    }
}
