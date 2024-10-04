package parser;

import commands.AddCommand;
import commands.Command;
import commands.ExitCommand;
import commands.*;
import exceptions.DootException;

public class Parser {


    public static Command findCommand(String command) throws DootException {
        boolean containsSpace = command.contains(" ");
        String cmd = containsSpace ? command.substring(0, command.indexOf(" ")) : command;
        String args = containsSpace ? command.substring(command.indexOf(" ") + 1) : "";
        if (AddCommand.COMMAND_WORDS.contains(cmd)){
            return new AddCommand(cmd, args);
        }
        return switch (cmd) {
            case ExitCommand.COMMAND_WORD -> new ExitCommand(cmd, args);
            case DeleteCommand.COMMAND_WORD -> new DeleteCommand(cmd, args);
            case ListCommand.COMMAND_WORD -> new ListCommand(cmd, args);
            case MarkCommand.COMMAND_WORD -> new MarkCommand(cmd, args);
            case UnmarkCommand.COMMAND_WORD -> new UnmarkCommand(cmd, args);
            default -> throw new DootException("Unknown command: " + cmd);
        };

    }
}
