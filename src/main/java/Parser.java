import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Commands.Command;
import Commands.IncorrectCommand;
import Commands.HelpCommand;
import Commands.ListCommand;
import Commands.TodoCommand;
import Commands.ByeCommand;
import Commands.TaskCommand;
import Commands.DeadlineCommand;
import Commands.EventCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Commands.DeleteCommand;

import static Ui.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT));
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        
        final String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");
        if (arguments.startsWith(" ")) {
            arguments = arguments.substring(1);
        }

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case TaskCommand.COMMAND_WORD:
            return prepareTask(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(Integer.parseInt(arguments) - 1);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(Integer.parseInt(arguments) - 1);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
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
        return new TodoCommand(args);
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareTask(String args) {
        return new TaskCommand(args);
    }

    private Command prepareDeadline(String args) {
        String[] deadlineCommand = args.split(" /by ");
        // if (deadlineCommand.length < 2 || deadlineCommand[0].isBlank() || deadlineCommand[1].isBlank()) {
        //     throw new CuboneMissingParameterError();
        // }
        return new DeadlineCommand(deadlineCommand[0], deadlineCommand[1]);
    }

    private Command prepareEvent(String args) {
        String[] eventCommand = args.split(" /from ");
        String[] eventCommand2 = eventCommand[1].split(" /to ");
        return new EventCommand(eventCommand[0], eventCommand2[0], eventCommand2[1]);
    }
}
