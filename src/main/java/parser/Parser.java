package parser;

import commands.AddCommand;
import commands.Command;
import commands.ExitCommand;
import commands.*;
import exceptions.DootException;

public class Parser {


    /**
     * Parses the given command to find which type of Command object to make. It then returns that Command object.
     *
     * @param command command to be parsed
     * @return Command object
     * @throws DootException Unknown command
     */
    public static Command findCommand(String command) throws DootException {
        boolean containsSpace = command.contains(" ");
        String cmd = containsSpace ? command.substring(0, command.indexOf(" ")) : command;
        String args = containsSpace ? command.substring(command.indexOf(" ") + 1) : "";
        if (AddCommand.COMMAND_WORDS.contains(cmd)) {
            return new AddCommand(cmd, args);
        }
        switch (cmd) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand(cmd, args);
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(cmd, args);
            case ListCommand.COMMAND_WORD:
                return new ListCommand(cmd, args);
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(cmd, args);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(cmd, args);
            case FindCommand.COMMAND_WORD:
                return new FindCommand(cmd, args);
            default:
                throw new DootException("Unknown command: " + cmd);
        }

    }
}
