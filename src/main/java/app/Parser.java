package app;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ClearCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.InvalidCommandException;

/**
 * The Parser class is responsible for interpreting the user's input
 * and returning the appropriate Command object. It ensures that user
 * input is correctly matched to known commands and throws an exception
 * if the input is invalid.
 */

public class Parser {

    public Parser() {}

    /**
     * Parses the user input and maps it to the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return The corresponding Command object based on the user input.
     * @throws InvalidCommandException If the input does not match a known command.
     */

    public Command parse(String userInput) throws InvalidCommandException {
        if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.toLowerCase().startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("todo")) {
            return new AddTodoCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            return new AddDeadlineCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("event")) {
            return new AddEventCommand(userInput);
        } else if (userInput.toLowerCase().startsWith("clear")) {
            return new ClearCommand();
        } else if (userInput.toLowerCase().startsWith("find")) {
            return new FindCommand(userInput);
        } else {
            throw new InvalidCommandException("Unknown command: " + userInput);
        }
    }
}
