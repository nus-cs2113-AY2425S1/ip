package bosco.parser;

import bosco.command.Command;
import bosco.command.ListCommand;
import bosco.command.MarkCommand;
import bosco.command.UnmarkCommand;
import bosco.command.DeleteCommand;
import bosco.command.AddTodoCommand;
import bosco.command.AddDeadlineCommand;
import bosco.command.AddEventCommand;
import bosco.command.ExitCommand;

import bosco.exception.EmptyDescriptionException;
import bosco.exception.IllegalCommandException;
import bosco.exception.IllegalDateTimeException;
import bosco.exception.MissingPrefixException;

import java.time.LocalDateTime;

public class Parser {
    private static final String DEADLINE_PREFIX_BY = "/by";
    private static final String EVENT_PREFIX_FROM = "/from";
    private static final String EVENT_PREFIX_TO = "/to";

    public Command parseCommand(String userInputString)
            throws IllegalCommandException, EmptyDescriptionException,
            MissingPrefixException, IllegalDateTimeException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(userInputString);
        String commandType = commandTypeAndArgs[0];
        String commandArgs = commandTypeAndArgs[1];
        switch (commandType) {
        case "list":
            return new ListCommand();
        case "mark":
            return prepareMark(commandArgs);
        case "unmark":
            return prepareUnmark(commandArgs);
        case "delete":
            return prepareDelete(commandArgs);
        case "todo":
            return prepareAddTodo(commandArgs);
        case "deadline":
            return prepareAddDeadline(commandArgs);
        case "event":
            return prepareAddEvent(commandArgs);
        case "bye":
        case "exit":
            return new ExitCommand();
        default:
            throw new IllegalCommandException();
        }
    }

    private String[] splitCommandTypeAndArgs(String userInputString) {
        String[] stringParts = userInputString.split("\\s+", 2);
        return stringParts.length == 2 ? stringParts : new String[] {stringParts[0], ""};
    }

    private static String removePrefix(String inputStr, String prefix) {
        return inputStr.replace(prefix, "");
    }

    private Command prepareMark(String CommandArgs) {
        int targetNumber = Integer.parseInt(CommandArgs);
        return new MarkCommand(targetNumber);
    }

    private Command prepareUnmark(String CommandArgs) {
        int targetNumber = Integer.parseInt(CommandArgs);
        return new UnmarkCommand(targetNumber);
    }

    private Command prepareDelete(String commandArgs) {
        int targetNumber = Integer.parseInt(commandArgs);
        return new DeleteCommand(targetNumber);
    }

    private Command prepareAddTodo(String commandArgs) throws EmptyDescriptionException {
        String description = commandArgs.strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return new AddTodoCommand(description);
    }

    private Command prepareAddDeadline(String commandArgs)
            throws MissingPrefixException, EmptyDescriptionException, IllegalDateTimeException {
        int indexOfByPrefix = commandArgs.indexOf(DEADLINE_PREFIX_BY);
        if (indexOfByPrefix == -1) {
            throw new MissingPrefixException(DEADLINE_PREFIX_BY);
        }
        String description = commandArgs.substring(0, indexOfByPrefix).strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String by = removePrefix(commandArgs.substring(indexOfByPrefix), DEADLINE_PREFIX_BY).strip();
        try {
            LocalDateTime byDateTime = DateTimeParser.parseDateTime(by);
            return new AddDeadlineCommand(description, byDateTime);
        } catch (IllegalArgumentException e) {
            throw new IllegalDateTimeException();
        }
    }

    private Command prepareAddEvent(String commandArgs)
            throws MissingPrefixException, EmptyDescriptionException, IllegalDateTimeException {
        int indexOfFromPrefix = commandArgs.indexOf(EVENT_PREFIX_FROM);
        if (indexOfFromPrefix == -1) {
            throw new MissingPrefixException(EVENT_PREFIX_FROM);
        }
        int indexOfToPrefix = commandArgs.indexOf(EVENT_PREFIX_TO);
        if (indexOfToPrefix == -1) {
            throw new MissingPrefixException(EVENT_PREFIX_TO);
        }
        String description = commandArgs.substring(0, indexOfFromPrefix).strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String from = removePrefix(commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                EVENT_PREFIX_FROM).strip();
        String to = removePrefix(commandArgs.substring(indexOfToPrefix), EVENT_PREFIX_TO).strip();
        try {
            LocalDateTime fromDateTime = DateTimeParser.parseDateTime(from);
            LocalDateTime toDateTime = DateTimeParser.parseDateTime(to);
            return new AddEventCommand(description, fromDateTime, toDateTime);
        } catch (IllegalArgumentException e) {
            throw new IllegalDateTimeException();
        }
    }
}
