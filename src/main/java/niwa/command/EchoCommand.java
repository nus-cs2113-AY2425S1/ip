package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import java.util.ArrayList;

/**
 * The {@code EchoCommand} class handles echoing back a string input by the user.
 */
public class EchoCommand extends Command {
    public static final String COMMAND_WORD = "echo";
    public static final String COMMAND_GUIDE = "echo [string]: Echo the string.";
    public static final String[] COMMAND_KEYWORDS = {""};

    /**
     * Checks if the provided arguments are valid.
     *
     * @return true if valid; false otherwise.
     */
    public boolean isValidArguments() {
        return arguments.size() == COMMAND_KEYWORDS.length &&
                arguments.containsKey(COMMAND_KEYWORDS[0]);
    }

    /**
     * Echoes a string provided by the user.
     *
     * @return A {@code CommandResult} containing the echoed string.
     * @throws NiwaInvalidArgumentException If the arguments are invalid.
     */
    @Override
    public CommandResult execute() throws NiwaInvalidArgumentException {
        if (!isValidArguments()) {
            throw new NiwaInvalidArgumentException(COMMAND_GUIDE); // Invalid arguments
        }

        String inputString = arguments.get(COMMAND_KEYWORDS[0]); // Get the input string

        ArrayList<String> messages = new ArrayList<>(); // Messages for command execution
        messages.add(inputString); // Add the echoed string to messages

        return new CommandResult(messages); // Return the result
    }
}
