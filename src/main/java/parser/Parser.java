package parser;

import command.Command;
import command.AddCommand;
import command.ListCommand;
import command.ExitCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.DeadlineCommand;
import command.EventCommand; 
import command.FindCommand;
import exception.InvalidTaskNumberException;
import exception.UnknownCommandException;

public class Parser {
    public static Command parse(String userInput) throws UnknownCommandException, InvalidTaskNumberException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                if (parts.length < 2) {
                    throw new UnknownCommandException("The 'todo' command requires a description.");
                }
                return new AddCommand(userInput);
            case "deadline":
                if (parts.length < 2) {
                    throw new UnknownCommandException("The 'deadline' command requires a description and a date.");
                }
                return new DeadlineCommand(parts[1]);
            case "event":
                if (parts.length < 2) {
                    throw new UnknownCommandException("The 'event' command requires a description and dates.");
                }
                return new EventCommand(parts[1]);
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "delete":
                return new DeleteCommand(parts[1]);
            case "list":
                return new ListCommand();
            case "find":
                if (parts.length < 2) {
                    throw new UnknownCommandException("The 'find' command requires a keyword.");
                }
                return new FindCommand(parts[1]);
            case "bye":
                return new ExitCommand();
            default:
                throw new UnknownCommandException("Invalid command.");
        }
    }
}