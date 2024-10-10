package atom.parser;

import atom.command.Command;
import atom.command.AddCommand;
import atom.command.DeleteCommand;
import atom.command.ExitCommand;
import atom.command.InvalidCommand;
import atom.command.ListCommand;
import atom.command.MarkCommand;
import atom.command.FindCommand;
import atom.command.FilterCommand;

/**
 * Represents a parser which makes sense of the user command.
 * <p>
 * Parses the full user command and returns a <code>Command</code> type object.
 */
public class Parser {

    /**
     * Returns the respective <code>Command</code> type object based on parsed command.
     *
     * @param fullCommand User input command.
     * @return <code>Command</code> type object.
     */
    public static Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ");
        String command = words[0].toLowerCase();

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }

        if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        switch (command) {
        case "mark":
        case "unmark":
            return new MarkCommand(words, command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(words, fullCommand, command);
        case "delete":
            return new DeleteCommand(words);
        case "find":
            return new FindCommand(words, fullCommand);
        case "filter":
            return new FilterCommand(words, fullCommand);
        default:
            return new InvalidCommand();
        }
    }
}
