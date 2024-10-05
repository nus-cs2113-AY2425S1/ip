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
import exception.InvalidTaskNumberException;
import exception.UnknownCommandException;

public class Parser {

    public static Command parse(String userInput) throws UnknownCommandException, InvalidTaskNumberException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                return new AddCommand(userInput);
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "delete":
                return new DeleteCommand(parts[1]); 
            case "deadline":
                return new DeadlineCommand(parts[1]); 
            case "event":
                return new EventCommand(parts[1]); 
            case "bye":
                return new ExitCommand();
            default:
                throw new UnknownCommandException("Invalid command.");
        }
    }
}
