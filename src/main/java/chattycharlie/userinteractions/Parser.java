package chattycharlie.userinteractions;

import chattycharlie.commands.CommandType;

/**
 * Represents a parser that interprets user input and determines the command type.
 * The <code>Parser</code> class helps to extract the command from user input.
 */
public class Parser {

    /**
     * Parses the user input to determine the command type.
     *
     * @param input the user input as a string.
     * @return the corresponding <code>CommandType</code> for the given input.
     * @throws IllegalArgumentException if the input does not match any known command.
     */
    public CommandType getCommand(String input) {
        String firstWord = input.trim().split(" ")[0];
        return CommandType.valueOf(firstWord.toUpperCase());
    }
}
