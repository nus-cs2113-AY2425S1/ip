package app;

import Commands.*;
import exceptions.InvalidCommandException;
import taskmanager.Storage;

public class Parser {

    public Parser() {}

    public Command parse(String userInput, Storage storage) throws InvalidCommandException {
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