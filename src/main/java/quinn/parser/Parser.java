package quinn.parser;

import quinn.command.AddDeadlineCommand;
import quinn.command.AddEventCommand;
import quinn.command.AddToDoCommand;
import quinn.command.Command;
import quinn.command.CommandType;
import quinn.command.DeleteCommand;
import quinn.command.ExitCommand;
import quinn.command.ListCommand;
import quinn.command.MarkCommand;
import quinn.command.UnmarkCommand;
import quinn.exception.QuinnException;

public class Parser {
    public Command parse(String commandLine) throws QuinnException {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        CommandType commandType = validateCommand(commandLineParts[0].toLowerCase());

        if (commandType == null) {
            throw new QuinnException("INVALID COMMAND. Please try again!");
        }

        String commandInfo = getCommandInfo(commandLineParts, commandType);

        return initialiseCommand(commandType, commandInfo);
    }

    private CommandType validateCommand(String commandType) {
        for (CommandType c : CommandType.values()) {
            if (c.getLabel().equals(commandType)) {
                return c;
            }
        }

        return null;
    }

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

    private Command initialiseCommand(CommandType commandType, String commandInfo) throws QuinnException {
        Command command;
        int taskNum;
        String taskDescription;
        String taskInfo;

        // Enhanced 'switch'
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
        };

        return command;
    }

    private boolean isCommandInfoPresent(String commandInfo) {
        return !commandInfo.trim().isEmpty();
    }

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

    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new QuinnException("The description of a todo cannot be empty!");
        }
    }

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
}
