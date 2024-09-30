import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Commands.Command;
import Commands.IncorrectCommand;
import Commands.HelpCommand;
import Commands.ListCommand;
import Commands.TodoCommand;

import static Ui.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+) (?<arguments>.*)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case "list":
            return new ListCommand();
        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareTodo(String args) {
        System.out.println(args);
        return new TodoCommand(args);
    }
}
