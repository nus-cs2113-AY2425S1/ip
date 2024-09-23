package ui;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ShowCommand;
import exception.InvalidCommandException;

import static constants.Command.BYE_COMMAND;
import static constants.Command.COMMAND_INDEX;
import static constants.Command.DEADLINE_COMMAND;
import static constants.Command.DELETE_COMMAND;
import static constants.Command.EVENT_COMMAND;
import static constants.Command.FIND_COMMAND;
import static constants.Command.LIST_COMMAND;
import static constants.Command.MARK_COMMAND;
import static constants.Command.SHOW_COMMAND;
import static constants.Command.TASK_INDEX;
import static constants.Command.TASK_STATUS_INDEX;
import static constants.Command.TODO_COMMAND;
import static constants.Command.UNMARK_COMMAND;
import static constants.Regex.BY_REGEX;
import static constants.Regex.DEADLINE_BY_INDEX;
import static constants.Regex.DEADLINE_NAME_INDEX;
import static constants.Regex.EMPTY_REGEX;
import static constants.Regex.FROM_PREFIX;
import static constants.Regex.SPACE_REGEX;
import static constants.Regex.TASK_DONE_INDICATOR;
import static constants.Regex.TASK_STATUS_DELIMITER_REGEX;
import static constants.Regex.TO_PREFIX;

public class Parser {
    public Command getCommand(String input, boolean fromUserInput) throws InvalidCommandException {
        String trimmedInput = input.trim();
        String[] inputList = getInputList(trimmedInput);
        return switch (inputList[COMMAND_INDEX]) {
            case BYE_COMMAND -> new ByeCommand();
            case LIST_COMMAND -> new ListCommand();
            case MARK_COMMAND -> new MarkCommand(true, trimmedInput);
            case UNMARK_COMMAND -> new MarkCommand(false, trimmedInput);
            case TODO_COMMAND -> new AddTodoCommand(trimmedInput, fromUserInput);
            case DEADLINE_COMMAND -> new AddDeadlineCommand(trimmedInput, fromUserInput);
            case EVENT_COMMAND -> new AddEventCommand(trimmedInput, fromUserInput);
            case DELETE_COMMAND -> new DeleteCommand(trimmedInput);
            case SHOW_COMMAND -> new ShowCommand(trimmedInput);
            case FIND_COMMAND -> new FindCommand(trimmedInput);
            default -> throw new InvalidCommandException();
        };
    }

    public String[] getInputList(String input) {
        return input.split(SPACE_REGEX);
    }

    public String removeMarkPrefix(String input) {
        return input.replace(UNMARK_COMMAND, EMPTY_REGEX).replace(MARK_COMMAND, EMPTY_REGEX).trim();
    }

    public String getTodo(String input) {
        return input.replace(TODO_COMMAND, "").trim();
    }

    public String removeDeadlinePrefix(String input) {
        return input.replace(DEADLINE_COMMAND, EMPTY_REGEX);
    }

    public String extractDeadlineName(String input) {
        return input.split(BY_REGEX)[DEADLINE_NAME_INDEX].trim();
    }

    public String extractDeadlineBy(String input) {
        String[] inputList = input.split(BY_REGEX);
        if (inputList.length == 1) {
            return "";
        }
        return inputList[DEADLINE_BY_INDEX].trim();
    }

    public String removeEventPrefix(String input) {
        return input.replace(EVENT_COMMAND, EMPTY_REGEX);
    }

    public String extractEventName(String input, int indexOfFrom) {
        return input.substring(0, indexOfFrom).trim();
    }

    public String extractFromString(String input, int indexOfFrom, int indexOfTo) {
        return input.substring(indexOfFrom, indexOfTo).replace(FROM_PREFIX, EMPTY_REGEX).trim();
    }

    public String extractToString(String input, int indexOfTo) {
        return input.substring(indexOfTo).replace(TO_PREFIX, EMPTY_REGEX).trim();
    }

    public String removeDeletePrefix(String input) {
        return input.replace(DELETE_COMMAND, EMPTY_REGEX).trim();
    }

    public boolean getTaskDone(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_STATUS_INDEX].equals(TASK_DONE_INDICATOR);
    }

    public String getCommandString(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_INDEX];
    }

    public String removeShowPrefix(String input) {
        return input.replace(SHOW_COMMAND, EMPTY_REGEX).trim();
    }

    public String removeFindPrefix(String input) {
        return input.replace(FIND_COMMAND, EMPTY_REGEX).trim();
    }
}
