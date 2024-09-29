package grok.parser;

import grok.commands.*;
import grok.GrokException;

public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input string.
     * @return The command corresponding to the user input.
     * @throws GrokException If the input is unrecognized.
     */
    public static Command parse(String input) throws GrokException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(words[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(words[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "find":  // New case for find command
            return new FindCommand(words[1]);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command, words[1]);
        default:
            throw new GrokException("I'm sorry, I don't understand that command.");
        }
    }
}
