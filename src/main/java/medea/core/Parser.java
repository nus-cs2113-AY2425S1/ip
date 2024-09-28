package medea.core;

import medea.command.DeleteCommand;
import medea.command.InvalidCommand;
import medea.command.ExitCommand;
import medea.command.ListCommand;
import medea.command.FindCommand;
import medea.command.Command;
import medea.command.create.task.DeadlineCommand;
import medea.command.create.task.EventCommand;
import medea.command.create.task.TodoCommand;
import medea.command.update.done.MarkDoneCommand;
import medea.command.update.done.UnmarkDoneCommand;
import medea.exceptions.MedeaException;

/**
 * The {@code Parser} class is responsible for interpreting user input commands
 * and converting them into executable actions within the application.
 */
public class Parser {

    /**
     * Parses the given command string and returns the corresponding {@code Command} object.
     *
     * @param fullCommand the complete command string entered by the user
     * @return the {@code Command} object representing the parsed command
     */
    public Command parse(String fullCommand) {
        String[] inputArguments = fullCommand.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";


        switch (commandString) {
        case ListCommand.COMMAND_WORD: return createListCommand();
        case ExitCommand.COMMAND_WORD: return createExitCommand();
        case DeleteCommand.COMMAND_WORD: return createDeleteCommand(argumentString);
        case MarkDoneCommand.COMMAND_WORD: return createMarkCommand(argumentString);
        case UnmarkDoneCommand.COMMAND_WORD: return createUnmarkCommand(argumentString);
        case TodoCommand.COMMAND_WORD: return createTodoCommand(argumentString);
        case DeadlineCommand.COMMAND_WORD: return createDeadlineCommand(argumentString);
        case EventCommand.COMMAND_WORD: return createEventCommand(argumentString);
        case FindCommand.COMMAND_WORD: return createFindCommand(argumentString);
        default: return createInvalidCommand();
        }
    }

    /**
     * Creates an {@code InvalidCommand} object.
     *
     * @return the {@code InvalidCommand} object
     */
    private Command createInvalidCommand() {
        return new InvalidCommand();
    }

    /**
     * Creates a {@code ListCommand} object.
     *
     * @return the {@code ListCommand} object
     */
    private Command createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates an {@code ExitCommand} object.
     *
     * @return the {@code ExitCommand} object
     */
    private Command createExitCommand() {
        return new ExitCommand();
    }

    /**
     * Parses the argument string to extract the task index and creates a {@code DeleteCommand} object.
     *
     * @param argumentString the argument string containing the task index
     * @return the {@code DeleteCommand} object
     * @throws MedeaException if the task index is out of range or invalid
     */
    private Command createDeleteCommand(String argumentString) {
        int index = parseTaskIndex(argumentString);
        return new DeleteCommand(index);
    }

    /**
     * Parses the argument string to extract the task index and creates a {@code MarkDoneCommand} object.
     *
     * @param argumentString the argument string containing the task index
     * @return the {@code MarkDoneCommand} object
     * @throws MedeaException if the task index is out of range or invalid
     */
    private Command createMarkCommand(String argumentString) {
        int index = parseTaskIndex(argumentString);
        return new MarkDoneCommand(index);
    }

    /**
     * Parses the argument string to extract the task index and creates an {@code UnmarkDoneCommand} object.
     *
     * @param argumentString the argument string containing the task index
     * @return the {@code UnmarkDoneCommand} object
     * @throws MedeaException if the task index is out of range or invalid
     */
    private Command createUnmarkCommand(String argumentString) {
        int index = parseTaskIndex(argumentString);
        return new UnmarkDoneCommand(index);
    }

    /**
     * Parses the task index from the given string.
     *
     * @param taskIndex the string containing the task index
     * @return the parsed task index
     * @throws MedeaException if the task index is out of range or invalid
     */
    private int parseTaskIndex(String taskIndex) {
        try {
            return Integer.parseInt(taskIndex.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MedeaException("Invalid task index. Please provide a valid number.");
        }
    }

    /**
     * Creates a {@code TodoCommand} object with the specified description.
     *
     * @param description the description of the todo task
     * @return the {@code TodoCommand} object
     */
    private Command createTodoCommand(String description) {
        return new TodoCommand(description);
    }

    /**
     * Creates a {@code FindCommand} object with the specified filter word.
     *
     * @param filterWord the word to filter tasks by
     * @return the {@code FindCommand} object
     */
    private Command createFindCommand(String filterWord) {
        return new FindCommand(filterWord);
    }

    /**
     * Parses the argument string to extract the task description and deadline,
     * Then creates a {@code DeadlineCommand} object.
     *
     * @param argumentString the argument string containing the task description and deadline
     * @return the {@code DeadlineCommand} object
     * @throws MedeaException if the argument format is invalid
     */
    private Command createDeadlineCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /by ");
        if (arguments.length != 2) {
            throw new MedeaException("Invalid deadline command. " +
                                     "Please provide a task description and a deadline using '/by'.");
        }

        String description = arguments[0];
        String by = arguments[1];

        return new DeadlineCommand(description, by);
    }

    /**
     * Parses the argument string to extract the task description, start time, and end time
     * Then creates an {@code EventCommand} object.
     *
     * @param argumentString the argument string containing the task description, start time, and end time
     * @return the {@code EventCommand} object
     * @throws MedeaException if the argument format is invalid
     */
    private Command createEventCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");

        if (arguments.length != 3) {
            throw new MedeaException("Invalid event command. " +
                                     "Please provide a task, start time, and end time using '/from' and '/to'.");
        }

        String description = arguments[0];
        String from = arguments[1];
        String to = arguments[2];

        return new EventCommand(description, from, to);
    }

    /**
     * Parses the argument string using the specified delimiters.
     *
     * @param argumentString the raw argument string to parse
     * @param delimiters     the delimiters to use for parsing a given command
     * @return an array of parsed arguments
     */
    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
