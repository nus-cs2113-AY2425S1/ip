package nell.parser;

import nell.list.TaskList;
import nell.command.Command;
import nell.command.DeadlineCommand;
import nell.command.EventCommand;
import nell.command.IncorrectCommand;
import nell.command.FindCommand;
import nell.command.ListCommand;
import nell.command.MarkCommand;
import nell.command.RemoveCommand;
import nell.command.SearchCommand;
import nell.command.ToDoCommand;
import nell.command.UnmarkCommand;
import nell.common.Messages;

import java.time.format.DateTimeParseException;

/**
 * Handles the parsing and execution of commands entered by the user into the UI
 */
public class Parser {
    private static final int SINGLE_WORD_LENGTH = 1;
    private static final int MULTI_WORD_LENGTH = 2;

    private final TaskList tasks;

    /**
     * Constructs a parser object with a given task list
     *
     * @param tasks The task list to be used by the object
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a Command object based upon the specified command
     * If the command is invalid, returns an incorrect command
     *
     * @param command The command word for the command
     * @return Command object based upon command
     */
    public Command parseCommand(String command) {
        String[] commandWords = command.split(" ", 2);
        switch (commandWords.length) {
        case SINGLE_WORD_LENGTH:
            return parseSingleWordCommand(command);

        case MULTI_WORD_LENGTH:
            try {
                return parseMultiWordCommand(commandWords);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand(tasks, Messages.INVALID_DATE_TIME_MESSAGE);
            }

        default:
            return new IncorrectCommand(tasks);
        }
    }

    /**
     * Returns a Command object based upon the specified single-word command
     * If the command is invalid, returns an incorrect command
     *
     * @param command The command word of the single-word command
     * @return Command object based upon single-word command
     */
    private Command parseSingleWordCommand(String command) {
        if (command.equals("list")) {
            return new ListCommand(tasks);
        } else {
            return new IncorrectCommand(tasks);
        }
    }

    /**
     * Returns a Command object based upon the specified multi-word command
     * If the command is invalid, returns an incorrect command
     *
     * @param commandWords The command words of the multi-word command
     * @return Command object based upon multi-word command
     * @throws DateTimeParseException If dates cannot be parsed for commands that take in dates
     */
    private Command parseMultiWordCommand(String[] commandWords) throws DateTimeParseException {
        switch (commandWords[0]) {
        case "mark":
            try {
                return parseCommandWithIndex("mark", commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.MARK_ERROR_MESSAGE);
            }

        case "unmark":
            try {
                return parseCommandWithIndex("unmark", commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.UNMARK_ERROR_MESSAGE);
            }

        case "todo":
            try {
                return new ToDoCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.TODO_ERROR_MESSAGE);
            }

        case "deadline":
            try {
                return new DeadlineCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.DEADLINE_ERROR_MESSAGE);
            }

        case "event":
            try {
                return new EventCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.EVENT_ERROR_MESSAGE);
            }

        case "find":
            try {
                return new FindCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.FIND_ERROR_MESSAGE);
            }

        case "search":
            try {
                return new SearchCommand(tasks, commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.SEARCH_ERROR_MESSAGE);
            }

        case "remove":
            try {
                return parseCommandWithIndex("remove", commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand(tasks, Messages.REMOVE_ERROR_MESSAGE);
            }

        default:
            return new IncorrectCommand(tasks);
        }
    }

    /**
     * Returns a Command object based upon a specified command keyword and index
     *
     * @param commandWord The specified command keyword
     * @param index The task index
     * @return Command object corresponding to command keyword
     */
    private Command parseCommandWithIndex(String commandWord, String index) {
        try {
            int taskIndex = Integer.parseInt(index);
            switch (commandWord) {
            case "mark":
                return new MarkCommand(tasks, taskIndex);

            case "unmark":
                return new UnmarkCommand(tasks, taskIndex);

            case "remove":
                return new RemoveCommand(tasks, taskIndex);

            default:
                return new IncorrectCommand(tasks);
            }
        } catch (NumberFormatException e){
            switch (commandWord) {
            case "mark":
                return new IncorrectCommand(tasks, Messages.MARK_ERROR_MESSAGE);

            case "unmark":
                return new IncorrectCommand(tasks, Messages.UNMARK_ERROR_MESSAGE);

            case "remove":
                return new IncorrectCommand(tasks, Messages.REMOVE_ERROR_MESSAGE);

            default:
                return new IncorrectCommand(tasks);
            }
        }
    }
}
