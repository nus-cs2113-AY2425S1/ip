package quinn.parser;

import quinn.command.AddDeadlineCommand;
import quinn.command.AddEventCommand;
import quinn.command.AddToDoCommand;
import quinn.command.Command;
import quinn.command.CommandType;
import quinn.command.DeleteCommand;
import quinn.command.ExitCommand;
import quinn.command.FindCommand;
import quinn.command.ListCommand;
import quinn.command.MarkCommand;
import quinn.command.UnmarkCommand;
import quinn.exception.QuinnException;


/**
 * The Parser class is responsible for interpreting user input and converting it into
 * appropriate Command objects in the Quinn task management application.
 *
 * This class handles the parsing of various command types, including task creation,
 * listing, marking, unmarking, deletion, and searching. It validates user input and
 * throws QuinnExceptions for invalid or incomplete commands.
 *
 */
public class Parser {
    /**
     * Parses a command line input and returns the corresponding Command object.
     *
     * @param commandLine the full command line input from the user
     * @return a Command object representing the parsed command
     * @throws QuinnException if the command is invalid or incomplete
     */
    public Command parse(String commandLine) throws QuinnException {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        CommandType commandType = validateCommand(commandLineParts[0].toLowerCase());

        if (commandType == null) {
            throw new QuinnException("INVALID COMMAND. Please try again!");
        }

        String commandInfo = getCommandInfo(commandLineParts, commandType);

        return initialiseCommand(commandType, commandInfo);
    }

    /**
     * Validates the command type against known command types.
     *
     * @param commandType the command type string to validate
     * @return the corresponding CommandType enum value, or null if invalid
     */
    private CommandType validateCommand(String commandType) {
        for (CommandType c : CommandType.values()) {
            if (c.getLabel().equals(commandType)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Extracts command information from the command line parts.
     *
     * @param commandLineParts the split parts of the command line
     * @param commandType the type of the command
     * @return the command information string
     * @throws QuinnException if the command format is invalid
     */
    private String getCommandInfo(String[] commandLineParts, CommandType commandType) throws QuinnException {
        String commandInfo;

        if (commandLineParts.length == 2) {
            commandInfo = commandLineParts[1];

            // If user inputs extra information for "bye" or "list" commands,
            // then it is not a valid command
            if ((commandType == CommandType.BYE) || (commandType == CommandType.LIST)) {
                throw new QuinnException("INVALID COMMAND. Please try again!");
            }
        } else {
            // For "bye" and "list" commands
            commandInfo = "";
        }

        return commandInfo;
    }

    /**
     * Initializes and returns the appropriate Command object based on the command type and information.
     *
     * @param commandType the type of the command
     * @param commandInfo additional information for the command
     * @return the initialized Command object
     * @throws QuinnException if there's an error in command initialization
     */
    private Command initialiseCommand(CommandType commandType, String commandInfo) throws QuinnException {
        Command command;
        int taskNum;
        String taskDescription;
        String taskInfo;
        String keyword;

        // Enhanced 'switch' (https://dev.to/nikhilxd/exploring-enhanced-switch-in-java-44fh)
        command = switch (commandType) {
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand();
            case MARK -> {
                taskNum = getTaskNumFromMarkCommand(commandInfo);
                yield new MarkCommand(taskNum);
            }
            case UNMARK -> {
                taskNum = getTaskNumFromUnmarkCommand(commandInfo);
                yield new UnmarkCommand(taskNum);
            }
            case DELETE -> {
                taskNum = getTaskNumFromDeleteCommand(commandInfo);
                yield new DeleteCommand(taskNum);
            }
            case TODO -> {
                taskDescription = getTaskDescriptionFromToDoCommand(commandInfo);
                yield new AddToDoCommand(taskDescription);
            }
            case DEADLINE -> {
                taskInfo = processTaskInfoFromDeadlineCommand(commandInfo);
                yield new AddDeadlineCommand(taskInfo);
            }
            case EVENT -> {
                taskInfo = processTaskInfoFromEventCommand(commandInfo);
                yield new AddEventCommand(taskInfo);
            }
            case FIND -> {
                keyword = getKeywordFromFindCommand(commandInfo);
                yield new FindCommand(keyword);
            }
        };

        return command;
    }

    /**
     * Checks if the command information is present and non-empty.
     *
     * @param commandInfo the command information string to check
     * @return true if command information is present, false otherwise
     */
    private boolean isCommandInfoPresent(String commandInfo) {
        return !commandInfo.trim().isEmpty();
    }

    /**
     * Extracts the task number from a mark command.
     *
     * @param commandInfo the command information string
     * @return the task number to be marked
     * @throws QuinnException if the task number is invalid or missing
     */
    private int getTaskNumFromMarkCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be marked as done!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be marked as done!");
        }
    }

    /**
     * Extracts the task number from an unmark command.
     *
     * @param commandInfo the command information string
     * @return the task number to be unmarked
     * @throws QuinnException if the task number is invalid or missing
     */
    private int getTaskNumFromUnmarkCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be marked as not done yet!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be marked as not done yet!");
        }
    }

    /**
     * Extracts the task number from a delete command.
     *
     * @param commandInfo the command information string
     * @return the task number to be deleted
     * @throws QuinnException if the task number is invalid or missing
     */
    private int getTaskNumFromDeleteCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be deleted!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be deleted!");
        }
    }

    /**
     * Extracts the task description from a todo command.
     *
     * @param commandInfo the command information string
     * @return the task description
     * @throws QuinnException if the description is empty
     */
    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new QuinnException("The description of a todo cannot be empty!");
        }
    }

    /**
     * Processes and validates the information for a deadline command.
     *
     * @param commandInfo the command information string
     * @return the processed deadline task information
     * @throws QuinnException if the deadline information is invalid or incomplete
     */
    private String processTaskInfoFromDeadlineCommand(String commandInfo) throws QuinnException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new QuinnException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /by before specifying the date/time]");
        } else {
            String[] deadlineInfoParts = commandInfo.split("/by", 2);

            if (deadlineInfoParts.length != 2) {
                throw new QuinnException("INVALID COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Please check that the description and date/time of a deadline is present!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }

            String deadlineDescription = deadlineInfoParts[0].trim();
            String deadlineByDateTime = deadlineInfoParts[1].trim();

            if (deadlineDescription.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of a deadline cannot be empty!");
            }

            if (deadlineByDateTime.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }

            return commandInfo;
        }
    }

    /**
     * Processes and validates the information for an event command.
     *
     * @param commandInfo the command information string
     * @return the processed event task information
     * @throws QuinnException if the event information is invalid or incomplete
     */
    private String processTaskInfoFromEventCommand(String commandInfo) throws QuinnException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new QuinnException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Specify the date/time with '/from /to']");
        } else {
            String[] eventInfoParts = commandInfo.split("/from|/to", 3);

            if (eventInfoParts.length != 3) {
                throw new QuinnException("INVALID COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Please check that the description and date/time of an event is present!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Specify the date/time with '/from /to']");
            }

            String eventDescription = eventInfoParts[0].trim();
            String eventFromDateTime = eventInfoParts[1].trim();
            String eventToDateTime = eventInfoParts[2].trim();

            if (eventDescription.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of an event cannot be empty!");
            }

            if (eventFromDateTime.isEmpty() || eventToDateTime.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Specify the date/time with '/from /to']");
            }

            return commandInfo;
        }
    }

    /**
     * Extracts the keyword from a find command.
     *
     * @param commandInfo the command information string
     * @return the search keyword
     * @throws QuinnException if the keyword is missing
     */
    private String getKeywordFromFindCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new QuinnException("Please enter the keyword to search for matching tasks!");
        }
    }
}
