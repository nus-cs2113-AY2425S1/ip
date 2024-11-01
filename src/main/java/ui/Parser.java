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

/**
 * This class is responsible for parsing user inputs and converting them into
 * appropriate command objects for execution in Bento.
 * It handles input string parsing, command identification, and extracting necessary details.
 */
public class Parser {

    /**
     * Parses the input string and returns the appropriate command based on the input.
     * Throws an InvalidCommandException if the command is not recognized.
     *
     * @param input The raw user input string.
     * @param fromUserInput Flag indicating if the input comes from the user or another source, such as a save file.
     * @return A Command object corresponding to the user input.
     * @throws InvalidCommandException if the input does not match any known command.
     */
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

    /**
     * Splits the input string into an array of words based on spaces.
     *
     * @param input The raw input string to split.
     * @return A string array containing individual words from the input.
     */
    public String[] getInputList(String input) {
        return input.split(SPACE_REGEX);
    }

    /**
     * Removes the "mark" or "unmark" prefix from the input string and trims the result.
     *
     * @param input The input string containing a mark or unmark command.
     * @return The input string without the "mark" or "unmark" prefix.
     */
    public String removeMarkPrefix(String input) {
        return input.replace(UNMARK_COMMAND, EMPTY_REGEX).replace(MARK_COMMAND, EMPTY_REGEX).trim();
    }

    /**
     * Extracts the task name of a ToDo from the input string.
     *
     * @param input The raw input string containing a to-do command.
     * @return The description of the to-do task.
     */
    public String getTodo(String input) {
        return input.replace(TODO_COMMAND, EMPTY_REGEX).trim();
    }

    /**
     * Removes the "deadline" prefix from the input string and trims the result.
     *
     * @param input The input string containing a deadline command.
     * @return The input string without the "deadline" prefix.
     */
    public String removeDeadlinePrefix(String input) {
        return input.replace(DEADLINE_COMMAND, EMPTY_REGEX);
    }

    /**
     * Extracts the task name of a Deadline from the input string.
     *
     * @param input The raw input string containing a deadline command.
     * @return The name of the Deadline.
     */
    public String extractDeadlineName(String input) {
        return input.split(BY_REGEX)[DEADLINE_NAME_INDEX].trim();
    }

    /**
     * Extracts the due date of a Deadline from the input string.
     *
     * @param input The raw input string containing a deadline command.
     * @return The due date of the Deadline.
     */
    public String extractDeadlineBy(String input) {
        String[] inputList = input.split(BY_REGEX);
        if (inputList.length == 1) {
            return "";
        }
        return inputList[DEADLINE_BY_INDEX].trim();
    }

    /**
     * Removes the "event" prefix from the input string and trims the result.
     *
     * @param input The input string containing an event command.
     * @return The input string without the "event" prefix.
     */
    public String removeEventPrefix(String input) {
        return input.replace(EVENT_COMMAND, EMPTY_REGEX);
    }

    /**
     * Extracts the name of an Event from the input string based on the position of "from".
     *
     * @param input The raw input string containing an event command.
     * @param indexOfFrom The index of the "from" prefix in the input string.
     * @return The name of the Event.
     */
    public String extractEventName(String input, int indexOfFrom) {
        return input.substring(0, indexOfFrom).trim();
    }

    /**
     * Extracts the "from" date of an Event from the input string.
     *
     * @param input The raw input string containing an event command.
     * @param indexOfFrom The index of the "from" prefix in the input string.
     * @param indexOfTo The index of the "to" prefix in the input string.
     * @return The "from" date of the event.
     */
    public String extractFromString(String input, int indexOfFrom, int indexOfTo) {
        return input.substring(indexOfFrom, indexOfTo).replace(FROM_PREFIX, EMPTY_REGEX).trim();
    }

    /**
     * Extracts the "to" date of an Event from the input string.
     *
     * @param input The raw input string containing an event command.
     * @param indexOfTo The index of the "to" prefix in the input string.
     * @return The "to" date of the Event.
     */
    public String extractToString(String input, int indexOfTo) {
        return input.substring(indexOfTo).replace(TO_PREFIX, EMPTY_REGEX).trim();
    }

    /**
     * Removes the "delete" prefix from the input string and trims the result.
     *
     * @param input The input string containing a delete command.
     * @return The input string without the "delete" prefix.
     */
    public String removeDeletePrefix(String input) {
        return input.replace(DELETE_COMMAND, EMPTY_REGEX).trim();
    }

    /**
     * Checks if a task is marked as done based on the saved task status string.
     *
     * @param line The line from the save file containing task status.
     * @return True if the task is done, false otherwise.
     */
    public boolean getTaskDone(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_STATUS_INDEX].equals(TASK_DONE_INDICATOR);
    }

    /**
     * Extracts the command string from a saved line of task data.
     *
     * @param line The line from the save file containing task data.
     * @return The command string.
     */
    public String getCommandString(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_INDEX];
    }

    /**
     * Removes the "show" prefix from the input string and trims the result.
     *
     * @param input The input string containing a show command.
     * @return The input string without the "show" prefix.
     */
    public String removeShowPrefix(String input) {
        return input.replace(SHOW_COMMAND, EMPTY_REGEX).trim();
    }

    /**
     * Removes the "find" prefix from the input string and trims the result.
     *
     * @param input The input string containing a find command.
     * @return The input string without the "find" prefix.
     */
    public String removeFindPrefix(String input) {
        return input.replace(FIND_COMMAND, EMPTY_REGEX).trim();
    }
}
